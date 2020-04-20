package requestclient

import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.HttpClient
import org.apache.http.util.EntityUtils
import org.apache.http.entity.StringEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpPut
import org.apache.http.client.methods.HttpPatch
import org.apache.http.client.methods.HttpDelete

final object HttpRequestClient {

  def makeGetRequest(url: String): String = {
    val getRequest = new HttpGet(url)
    getRequest.setHeader("Content-type", "application/json")
    val response = (new DefaultHttpClient).execute(getRequest)
    val responseString = EntityUtils.toString(response.getEntity())
    return responseString
  }

  def makePostRequest(url: String, requestBody: String): String = {
    val postRequest = new HttpPost(url)
    postRequest.setHeader("Content-type", "application/json")
    postRequest.setEntity(new StringEntity(requestBody))
    val response = (new DefaultHttpClient).execute(postRequest)
    val responseString = EntityUtils.toString(response.getEntity())
    return responseString
  }

  def makePutRequest(url: String, requestBody: String): String = {
    val putRequest = new HttpPut(url)
    putRequest.setHeader("Content-type", "application/json")
    putRequest.setEntity(new StringEntity(requestBody))
    val response = (new DefaultHttpClient).execute(putRequest)
    val responseString = EntityUtils.toString(response.getEntity())
    return responseString
  }

  def makePatchRequest(url: String, requestBody: String): String = {
    val patchRequest = new HttpPatch(url)
    patchRequest.setHeader("Content-type", "application/json")
    patchRequest.setEntity(new StringEntity(requestBody))
    val response = (new DefaultHttpClient).execute(patchRequest)
    val responseString = EntityUtils.toString(response.getEntity())
    return responseString
  }

  def makeDeleteRequest(url: String): String = {
    val deleteRequest = new HttpDelete(url)
    deleteRequest.setHeader("Content-type", "application/json")
    val response = (new DefaultHttpClient).execute(deleteRequest)
    val responseString = EntityUtils.toString(response.getEntity())
    return responseString
  }

}
