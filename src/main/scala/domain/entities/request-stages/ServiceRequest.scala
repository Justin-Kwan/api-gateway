package requeststages

import networking.HttpRequestClient

// have empty var waiting for request body
class ServiceRequest {

  protected var name:     String = _
  protected var url:      String = _
  protected var protocol: String = _
  protected var method:   String = _
  protected var body:     String = ""
  protected var requestFunction: (String, String) => String = _


  def setName(name: String): Unit = {
    this.name = name
  }

  def setUrl(url: String): Unit = {
    this.url = url
  }

  def setProtocol(protocol: String): Unit = {
    this.protocol = protocol
  }

  def setMethod(method: String): Unit = {
    this.method = method
  }

  def setBody(body: String): Unit = {
    this.body = body
  }

  def setRequestFunc(requestFunction: (String, String) => String): Unit = {
    this.requestFunction = requestFunction
  }

  def getName(): String = {
    return this.name
  }

  def getUrl(): String = {
    return this.url
  }

  def getProtocol(): String = {
    return this.protocol
  }

  def getMethod(): String = {
    return this.method
  }

  def getBody(): String = {
    return this.body
  }

  // test
  // def runRequest(): String = {
  //   this.requestFunction(request
  //   return response
  // }

}
