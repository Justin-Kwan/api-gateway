import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfterEach
import org.scalatest.PrivateMethodTester
import org.scalatest.Matchers
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.WireMockServer._
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig

import requestclient.HttpRequestClient

final class HttpRequestClientTest extends FunSpec with BeforeAndAfterEach {

  final val mockGetResponse1 = "{\"mockResponse: \": \"get\"}"
  final val mockGetResponse2 = "{\"mockResponse: \": \"get\", \"mockUrl: \": \"mockurl.com\"}"

  final val mockPostResponse1 = "{\"mockResponse: \": \"post\"}"
  final val mockPostResponse2 = "{\"mockResponse: \": \"post\", \"mockUrl: \": \"mockurl.com\"}"

  final val mockPutResponse1 = "{\"mockResponse: \": \"put\"}"
  final val mockPutResponse2 = "{\"mockResponse: \": \"put\", \"mockUrl: \": \"mockurl.com\"}"

  final val mockPatchResponse1 = "{\"mockResponse: \": \"patch\"}"
  final val mockPatchResponse2 = "{\"mockResponse: \": \"patch\", \"mockUrl: \": \"mockurl.com\"}"

  final val mockDeleteResponse1 = "{\"mockResponse: \": \"delete\"}"
  final val mockDeleteResponse2 = "{\"mockResponse: \": \"delete\", \"mockUrl: \": \"mockurl.com\"}"

  // mock request bodies
  final val mockRequestBody1 = "{\"mockRequest: \": \"mock request data\"}"
  final val mockRequestBody2 = "{\"mockRequest: \": \"mock request data\", \"info: \": \"mock\"}"

  // mock server constants
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
  private def configMockServerGetResponse(mockResponse: String): Unit = {
    this.mockServer.stubFor(get(urlPathEqualTo(MOCK_PATH))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody(mockResponse)))
  }

  // sets up a server stub with expected request body and response on mock post requests
  private def configMockServerPostResponse(mockRequestBody: String, mockResponse: String): Unit = {
    this.mockServer.stubFor(post(urlPathEqualTo(MOCK_PATH))
    .withRequestBody(containing(mockRequestBody))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody(mockResponse)))
  }

  private def configMockServerPutResponse(mockRequestBody: String, mockResponse: String): Unit = {
    this.mockServer.stubFor(put(urlPathEqualTo(MOCK_PATH))
    .withRequestBody(containing(mockRequestBody))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody(mockResponse)))
  }

  private def configMockServerPatchResponse(mockRequestBody: String, mockResponse: String): Unit = {
    this.mockServer.stubFor(patch(urlPathEqualTo(MOCK_PATH))
    .withRequestBody(containing(mockRequestBody))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody(mockResponse)))
  }

  private def configMockServerDeleteResponse(mockResponse: String): Unit = {
    this.mockServer.stubFor(delete(urlPathEqualTo(MOCK_PATH + MOCK_DELETE_RESOURCE))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody(mockResponse)))
  }

  describe("makeGetRequest() tests") {
    it("should make get request with mockResponse1") {
      this.configMockServerGetResponse(mockGetResponse1)
      val response = HttpRequestClient.makeGetRequest(MOCK_URL)
      assert(response == mockGetResponse1)
    }

    it("should make get request with mockResponse2") {
      this.configMockServerGetResponse(mockGetResponse2)
      val response = HttpRequestClient.makeGetRequest(MOCK_URL)
      assert(response == mockGetResponse2)
    }
  }

  describe("makePostRequest() tests") {
    it("should make post request with mockResponse1") {
      this.configMockServerPostResponse(mockRequestBody1, mockPostResponse1)
      val response = HttpRequestClient.makePostRequest(MOCK_URL, mockRequestBody1)
      assert(response == mockPostResponse1)
    }

    it("should make post request with mockResponse2") {
      this.configMockServerPostResponse(mockRequestBody2, mockPostResponse2)
      val response = HttpRequestClient.makePostRequest(MOCK_URL, mockRequestBody2)
      assert(response == mockPostResponse2)
    }
  }

  describe("makePutRequest() tests") {
    it("should make put request with mockResponse1") {
      this.configMockServerPutResponse(mockRequestBody1, mockPutResponse1)
      val response = HttpRequestClient.makePutRequest(MOCK_URL, mockRequestBody1)
      assert(response == mockPutResponse1)
    }

    it("should make put request with mockResponse2") {
      this.configMockServerPutResponse(mockRequestBody2, mockPutResponse2)
      val response = HttpRequestClient.makePutRequest(MOCK_URL, mockRequestBody2)
      assert(response == mockPutResponse2)
    }
  }

  describe("makePatchRequest() tests") {
    it("should make patch request with mockResponse1") {
      this.configMockServerPatchResponse(mockRequestBody1, mockPatchResponse1)
      val response = HttpRequestClient.makePatchRequest(MOCK_URL, mockRequestBody1)
      assert(response == mockPatchResponse1)
    }

    it("should make patch request with mockResponse2") {
      this.configMockServerPatchResponse(mockRequestBody2, mockPatchResponse2)
      val response = HttpRequestClient.makePatchRequest(MOCK_URL, mockRequestBody2)
      assert(response == mockPatchResponse2)
    }
  }

  describe("makeDeleteRequest() tests") {
    it("should make delete request with mockResponse1") {
      this.configMockServerDeleteResponse(mockDeleteResponse1)
      val response = HttpRequestClient.makeDeleteRequest(MOCK_URL + MOCK_DELETE_RESOURCE)
      assert(response == mockDeleteResponse1)
    }

    it("should make delete request with mockResponse2") {
      this.configMockServerDeleteResponse(mockDeleteResponse2)
      val response = HttpRequestClient.makeDeleteRequest(MOCK_URL + MOCK_DELETE_RESOURCE)
      assert(response == mockDeleteResponse2)
    }
  }

}
