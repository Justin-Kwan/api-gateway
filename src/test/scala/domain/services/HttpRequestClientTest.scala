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

  // mock server responses
  final val mockResponse1 = "{\"mockResponse: \": \"mock data\"}"
  final val mockResponse2 = "{\"mockResponse: \": \"mock data\", \"mockUrl: \": \"mockurl.com\"}"

  // mock request bodies
  final val mockRequestBody1 = "{\"mockRequest: \": \"mock request data\"}"
  final val mockRequestBody2 = "{\"mockRequest: \": \"mock request data\", \"info: \": \"mock\"}"

  // mock server constants
  final private val MOCK_PORT     = 5000
  final private val MOCK_HOSTNAME = "localhost"
  final private val MOCK_PATH     = "/v1/mock/api"
  final private val MOCK_URL      = "http://" + MOCK_HOSTNAME + ":5000" + MOCK_PATH
  final private val mockServer    = new WireMockServer(wireMockConfig().port(MOCK_PORT))

  override def beforeEach {
    mockServer.start()
  }

  override def afterEach {
    mockServer.stop()
  }

  // sets up a server stub and response on mock get requests
  private def configMockServerGetResponse(mockResponse: String): Unit = {
    this.mockServer.stubFor(get(urlPathEqualTo(MOCK_PATH))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody(mockResponse)))
  }

  // sets up a server stub and response on mock post requests
  private def configMockServerPostResponse(mockRequestBody: String, mockResponse: String): Unit = {
    this.mockServer.stubFor(post(urlPathEqualTo(MOCK_PATH))
    .withRequestBody(containing(mockRequestBody))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody(mockResponse)))
  }

  // asserts valid get request recieved by mock server
  private def assertValidGetRequest(): Unit = {
    this.mockServer.verify(getRequestedFor(urlPathEqualTo(MOCK_PATH))
    .withHeader("Content-Type", containing("application/json")))
  }

  // asserts valid post request (with request body) received by mock server
  private def assertValidPostRequest(): Unit = {
    this.mockServer.verify(postRequestedFor(urlPathEqualTo(MOCK_PATH))
    .withHeader("Content-Type", containing("application/json")))
  }

  describe("makeGetRequest() tests") {
    it("should make get request with mockResponse1") {
      this.configMockServerGetResponse(mockResponse1)
      val response = HttpRequestClient.makeGetRequest(MOCK_URL)
      this.assertValidGetRequest()
      assert(response == mockResponse1)
    }

    it("should make get request with mockResponse2") {
      this.configMockServerGetResponse(mockResponse2)
      val response = HttpRequestClient.makeGetRequest(MOCK_URL)
      this.assertValidGetRequest()
      assert(response == mockResponse2)
    }
  }

  describe("makePostRequest() tests") {
    it("should make post request with mockResponse1") {
      this.configMockServerPostResponse(mockRequestBody1, mockResponse1)
      val response = HttpRequestClient.makePostRequest(MOCK_URL, mockRequestBody1)
      this.assertValidPostRequest()
      assert(response == mockResponse1)
    }

    it("should make post request with mockResponse2") {
      this.configMockServerPostResponse(mockRequestBody2, mockResponse2)
      val response = HttpRequestClient.makePostRequest(MOCK_URL, mockRequestBody2)
      this.assertValidPostRequest()
      assert(response == mockResponse2)
    }
  }


}
