import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfterEach
import org.scalatest.PrivateMethodTester
import org.scalatest.Matchers
import org.scalatest.Ignore
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.WireMockServer._
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig

import networking.HttpRequestClient


//@Ignore // this test suite takes a very long time
final class HttpRequestClientTest extends FunSpec with BeforeAndAfterEach {

  final val MOCK_GET_RESPONSE1 = "{\"mockResponse: \": \"get\"}"
  final val MOCK_GET_RESPONSE2 = "{\"mockResponse: \": \"get\", \"mockUrl: \": \"mockurl.com\"}"

  final val MOCK_POST_RESPONSE1 = "{\"mockResponse: \": \"post\"}"
  final val MOCK_POST_RESPONSE2 = "{\"mockResponse: \": \"post\", \"mockUrl: \": \"mockurl.com\"}"

  final val MOCK_PUT_RESPONSE1 = "{\"mockResponse: \": \"put\"}"
  final val MOCK_PUT_RESPONSE2 = "{\"mockResponse: \": \"put\", \"mockUrl: \": \"mockurl.com\"}"

  final val MOCK_PATCH_RESPONSE1 = "{\"mockResponse: \": \"patch\"}"
  final val MOCK_PATCH_RESPONSE2 = "{\"mockResponse: \": \"patch\", \"mockUrl: \": \"mockurl.com\"}"

  final val MOCK_DELETE_RESPONSE1 = "{\"mockResponse: \": \"delete\"}"
  final val MOCK_DELETE_RESPONSE2 = "{\"mockResponse: \": \"delete\", \"mockUrl: \": \"mockurl.com\"}"

  // mock request bodies
  final val mockRequestBody1 = "{\"mockRequest: \": \"mock request data\"}"
  final val mockRequestBody2 = "{\"mockRequest: \": \"mock request data\", \"info: \": \"mock\"}"

  // mock request error responses
  final val CONNECTION_TIMEOUT_RESPONSE = "{ Downstream service connection timeout }"
  final val CONNECTION_REFUSED_RESPONSE = "{ Downstream service refused connection }"

  // mock server constants
  final private val LONG_DELAY           = 5500
  final private val SHORT_DELAY          = 4500
  final private val NO_DELAY             = 0
  final private val MOCK_PORT            = 5000
  final private val MOCK_HOSTNAME        = "localhost"
  final private val MOCK_PATH            = "/v1/mock/api"
  final private val MOCK_URL             = "http://" + MOCK_HOSTNAME + ":5000" + MOCK_PATH
  final private val MOCK_DELETE_RESOURCE = "/delete/resource/param"
  final private val mockServer           = new WireMockServer(wireMockConfig().port(MOCK_PORT))

  override def beforeEach {
    this.mockServer.start()
  }

  override def afterEach {
    this.mockServer.stop()
    this.mockServer.resetAll()
  }

  // sets up a server stub and response on mock get requests
  private def configMockServerGetResponse(mockResponse: String, delay: Int): Unit = {
    this.mockServer.stubFor(get(urlPathEqualTo(MOCK_PATH))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody(mockResponse)
    .withFixedDelay(delay)))
    Thread.sleep(2000)
  }

  // sets up a server stub with expected request body and response on mock post requests
  private def configMockServerPostResponse(mockRequestBody: String, mockResponse: String, delay: Int): Unit = {
    this.mockServer.stubFor(post(urlPathEqualTo(MOCK_PATH))
    .withRequestBody(containing(mockRequestBody))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody(mockResponse)
    .withFixedDelay(delay)))
    Thread.sleep(2000)
  }

  private def configMockServerPutResponse(mockRequestBody: String, mockResponse: String, delay: Int): Unit = {
    this.mockServer.stubFor(put(urlPathEqualTo(MOCK_PATH))
    .withRequestBody(containing(mockRequestBody))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody(mockResponse)
    .withFixedDelay(delay)))
    Thread.sleep(2000)
  }

  private def configMockServerPatchResponse(mockRequestBody: String, mockResponse: String, delay: Int): Unit = {
    this.mockServer.stubFor(patch(urlPathEqualTo(MOCK_PATH))
    .withRequestBody(containing(mockRequestBody))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody(mockResponse)
    .withFixedDelay(delay)))
    Thread.sleep(2000)
  }

  private def configMockServerDeleteResponse(mockResponse: String, delay: Int): Unit = {
    this.mockServer.stubFor(delete(urlPathEqualTo(MOCK_PATH + MOCK_DELETE_RESOURCE))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody(mockResponse)
    .withFixedDelay(delay)))
    Thread.sleep(2000)
  }

  describe("makeGetRequest() tests") {
    it("should make get request with mockResponse1") {
      this.configMockServerGetResponse(this.MOCK_GET_RESPONSE1, this.SHORT_DELAY)
      val response = HttpRequestClient.makeGetRequest(MOCK_URL)
      assert(response == this.MOCK_GET_RESPONSE1)
    }

    it("should make get request with mockResponse2") {
      this.configMockServerGetResponse(this.MOCK_GET_RESPONSE2, this.SHORT_DELAY)
      val response = HttpRequestClient.makeGetRequest(MOCK_URL)
      assert(response == this.MOCK_GET_RESPONSE2)
    }

    it("should fail making get request due to timeout") {
      this.configMockServerGetResponse(this.MOCK_GET_RESPONSE2, this.LONG_DELAY)
      val response = HttpRequestClient.makeGetRequest(MOCK_URL)
      assert(response == this.CONNECTION_TIMEOUT_RESPONSE)
    }

    it("should fail making get request due to no endpoint") {
      this.mockServer.stop()
      val response = HttpRequestClient.makeGetRequest(MOCK_URL)
      assert(response == this.CONNECTION_REFUSED_RESPONSE)
    }
  }

  describe("makeDeleteRequest() tests") {
    it("should make delete request with mockResponse1") {
      this.configMockServerDeleteResponse(this.MOCK_DELETE_RESPONSE1, this.SHORT_DELAY)
      val response = HttpRequestClient.makeDeleteRequest(MOCK_URL + MOCK_DELETE_RESOURCE)
      assert(response == this.MOCK_DELETE_RESPONSE1)
    }

    it("should make delete request with mockResponse2") {
      this.configMockServerDeleteResponse(this.MOCK_DELETE_RESPONSE2, this.SHORT_DELAY)
      val response = HttpRequestClient.makeDeleteRequest(MOCK_URL + MOCK_DELETE_RESOURCE)
      assert(response == this.MOCK_DELETE_RESPONSE2)
    }

    it("should fail making delete request due to timeout") {
      this.configMockServerDeleteResponse(this.MOCK_DELETE_RESPONSE2, this.LONG_DELAY)
      val response = HttpRequestClient.makeDeleteRequest(MOCK_URL + MOCK_DELETE_RESOURCE)
      assert(response == this.CONNECTION_TIMEOUT_RESPONSE)
    }

    it("should fail making delete request due to no endpoint") {
      this.mockServer.stop()
      val response = HttpRequestClient.makeDeleteRequest(MOCK_URL + MOCK_DELETE_RESOURCE)
      assert(response == this.CONNECTION_REFUSED_RESPONSE)
    }
  }

  describe("makePostRequest() tests") {
    it("should make post request with mockResponse1") {
      this.configMockServerPostResponse(mockRequestBody1, this.MOCK_POST_RESPONSE1, this.SHORT_DELAY)
      val response = HttpRequestClient.makePostRequest(MOCK_URL, mockRequestBody1)
      assert(response == this.MOCK_POST_RESPONSE1)
    }

    it("should make post request with mockResponse2") {
      this.configMockServerPostResponse(mockRequestBody2, this.MOCK_POST_RESPONSE2, this.SHORT_DELAY)
      val response = HttpRequestClient.makePostRequest(MOCK_URL, mockRequestBody2)
      assert(response == this.MOCK_POST_RESPONSE2)
    }

    it("should fail making post request due to timeout") {
      this.configMockServerPostResponse(mockRequestBody2, this.MOCK_POST_RESPONSE2, this.LONG_DELAY)
      val response = HttpRequestClient.makePostRequest(MOCK_URL, mockRequestBody2)
      assert(response == this.CONNECTION_TIMEOUT_RESPONSE)
    }

    it("should fail making post request due to no endpoint") {
      this.mockServer.stop()
      val response = HttpRequestClient.makePostRequest(MOCK_URL, mockRequestBody2)
      assert(response == this.CONNECTION_REFUSED_RESPONSE)
    }
  }

  describe("makePutRequest() tests") {
    it("should make put request with mockResponse1") {
      this.configMockServerPutResponse(mockRequestBody1, this.MOCK_PUT_RESPONSE1, this.SHORT_DELAY)
      val response = HttpRequestClient.makePutRequest(MOCK_URL, mockRequestBody1)
      assert(response == this.MOCK_PUT_RESPONSE1)
    }

    it("should make put request with mockResponse2") {
      this.configMockServerPutResponse(mockRequestBody2, this.MOCK_PUT_RESPONSE2, this.SHORT_DELAY)
      val response = HttpRequestClient.makePutRequest(MOCK_URL, mockRequestBody2)
      assert(response == this.MOCK_PUT_RESPONSE2)
    }

    it("should fail making put request due to timeout") {
      this.configMockServerPutResponse(mockRequestBody2, this.MOCK_PUT_RESPONSE2, this.LONG_DELAY)
      val response = HttpRequestClient.makePutRequest(MOCK_URL, mockRequestBody2)
      assert(response == this.CONNECTION_TIMEOUT_RESPONSE)
    }

    it("should fail making put request due to no endpoint") {
      this.mockServer.stop()
      val response = HttpRequestClient.makePutRequest(MOCK_URL, mockRequestBody2)
      assert(response == this.CONNECTION_REFUSED_RESPONSE)
    }
  }

  describe("makePatchRequest() tests") {
    it("should make patch request with mockResponse1") {
      this.configMockServerPatchResponse(mockRequestBody1, this.MOCK_PATCH_RESPONSE1, this.SHORT_DELAY)
      val response = HttpRequestClient.makePatchRequest(MOCK_URL, mockRequestBody1)
      assert(response == this.MOCK_PATCH_RESPONSE1)
    }

    it("should make patch request with mockResponse2") {
      this.configMockServerPatchResponse(mockRequestBody2, this.MOCK_PATCH_RESPONSE2, this.SHORT_DELAY)
      val response = HttpRequestClient.makePatchRequest(MOCK_URL, mockRequestBody2)
      assert(response == this.MOCK_PATCH_RESPONSE2)
    }

    it("should fail making patch request due to timeout") {
      this.configMockServerPatchResponse(mockRequestBody2, this.MOCK_PATCH_RESPONSE2, this.LONG_DELAY)
      val response = HttpRequestClient.makePatchRequest(MOCK_URL, mockRequestBody2)
      assert(response == this.CONNECTION_TIMEOUT_RESPONSE)
    }

    it("should fail making patch request due to no endpoint") {
      this.mockServer.stop()
      val response = HttpRequestClient.makePatchRequest(MOCK_URL, mockRequestBody2)
      assert(response == this.CONNECTION_REFUSED_RESPONSE)
    }
  }

  describe("getRequestFunc() tests") {
    it("should get a makeGetRequest function (asserting function's response)") {
      this.configMockServerGetResponse(this.MOCK_GET_RESPONSE1, this.NO_DELAY)
      val requestFunc: (String, String) => String = HttpRequestClient.getRequestFunc("get")
      val response = requestFunc(MOCK_URL, "")
      assert(response == this.MOCK_GET_RESPONSE1)
    }

    it("should get makeDeleteRequest function (asserting function's response)") {
      this.configMockServerDeleteResponse(this.MOCK_DELETE_RESPONSE1, this.NO_DELAY)
      val requestFunc: (String, String) => String = HttpRequestClient.getRequestFunc("delete")
      val response = requestFunc(MOCK_URL + MOCK_DELETE_RESOURCE, "")
      assert(response == this.MOCK_DELETE_RESPONSE1)
    }

    it("should get makePostRequest function (asserting function's response)") {
      this.configMockServerPostResponse(mockRequestBody1, this.MOCK_POST_RESPONSE1, this.NO_DELAY)
      val requestFunc: (String, String) => String = HttpRequestClient.getRequestFunc("post")
      val response = requestFunc(MOCK_URL, mockRequestBody1)
      assert(response == this.MOCK_POST_RESPONSE1)
    }

    it("should get makePutRequest function (asserting function's response)") {
      this.configMockServerPutResponse(mockRequestBody1, this.MOCK_PUT_RESPONSE1, this.NO_DELAY)
      val requestFunc: (String, String) => String = HttpRequestClient.getRequestFunc("put")
      val response = requestFunc(MOCK_URL, mockRequestBody1)
      assert(response == this.MOCK_PUT_RESPONSE1)
    }

    it("should get makePatchRequest function (asserting function's response)") {
      this.configMockServerPatchResponse(mockRequestBody1, this.MOCK_PATCH_RESPONSE1, this.NO_DELAY)
      val requestFunc: (String, String) => String = HttpRequestClient.getRequestFunc("patch")
      val response = requestFunc(MOCK_URL, mockRequestBody1)
      assert(response == this.MOCK_PATCH_RESPONSE1)
    }
  }

}
