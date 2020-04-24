package networking

import java.net.ConnectException
import java.net.SocketTimeoutException

import org.apache.http.client.config.RequestConfig
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder

import org.apache.http.util.EntityUtils
import org.apache.http.entity.StringEntity
import org.apache.http.client.methods.HttpRequestBase
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpPut
import org.apache.http.client.methods.HttpPatch
import org.apache.http.client.methods.HttpDelete
import org.apache.http.NoHttpResponseException


final object HttpRequestClient {

  private val CONNECTION_REFUSED_MSG       = "{ Downstream service refused connection }"
  private val CONNECTION_TIMEOUT_MSG       = "{ Downstream service connection timeout }"
  private val CONNECTION_DEFAULT_ERROR_MSG = "{ Downstream service request failed }"
  private val CONNECTION_TIMEOUT           = 5000 // milliseconds

  val config: RequestConfig = RequestConfig
  .custom()
  .setConnectTimeout(CONNECTION_TIMEOUT)
  .setConnectionRequestTimeout(CONNECTION_TIMEOUT)
  .setSocketTimeout(CONNECTION_TIMEOUT)
  .build()

  val httpClient: CloseableHttpClient = HttpClientBuilder
  .create()
  .setDefaultRequestConfig(config)
  .build()

  /**
   * Executes request based on received base request object's method.
   * Either a response body is returned or an http client exception
   * is handled with an error message returned.
   *
   * @param {HttpRequestBase} Request superclass for all request methods
   * @return {String} Api response or exception error message
   */
  private def executeRequest(requestObj: HttpRequestBase): String = {
    requestObj.setHeader("Content-type", "application/json")
    var responseString: String = ""

    try {
      val response = (this.httpClient).execute(requestObj)
      responseString = EntityUtils.toString(response.getEntity())
      response.close()
    }
    catch {
      case e: ConnectException => {
        responseString = CONNECTION_REFUSED_MSG
      }
      case e: NoHttpResponseException => {
        responseString = CONNECTION_TIMEOUT_MSG
      }
      case e: SocketTimeoutException => {
        responseString = CONNECTION_TIMEOUT_MSG
      }
      case _: Throwable => {
        responseString = CONNECTION_DEFAULT_ERROR_MSG
      }
    }

    return responseString
  }

  /**
   * overloaded methods for makeGetRequest and makeDeleteRequest
   */

  def makeGetRequest(url: String, requestBody: Option[String]): String = {
    val getRequest = new HttpGet(url)
    val responseString = this.executeRequest(getRequest)
    return responseString
  }

  def makeDeleteRequest(url: String, requestBody: Option[String]): String = {
    val deleteRequest = new HttpDelete(url)
    val responseString = this.executeRequest(deleteRequest)
    return responseString
  }

  def makePostRequest(url: String, requestBody: String): String = {
    val postRequest = new HttpPost(url)
    postRequest.setEntity(new StringEntity(requestBody))
    val responseString = this.executeRequest(postRequest)
    return responseString
  }

  def makePutRequest(url: String, requestBody: String): String = {
    val putRequest = new HttpPut(url)
    putRequest.setEntity(new StringEntity(requestBody))
    val responseString = this.executeRequest(putRequest)
    return responseString
  }

  def makePatchRequest(url: String, requestBody: String): String = {
    val patchRequest = new HttpPatch(url)
    patchRequest.setEntity(new StringEntity(requestBody))
    val responseString = this.executeRequest(patchRequest)
    return responseString
  }

  @Provides
  def getRequestFunction(requestMethod: String): (url: String, requestBody: String) => String {
    requestMethod match {
      case "get": => {
        return makeGetRequest
      }
      case "delete": => {
        return makeDeleteRequest
      }
      case "post": => {
        return makePostRequest
      }
      case "put": => {
        return makePutRequest
      }
      case "patch" => {
        return makePatchRequest
      }
    }
  }

}
