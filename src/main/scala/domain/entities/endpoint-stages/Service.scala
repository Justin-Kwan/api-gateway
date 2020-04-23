package servicestages

class Service /*@Inject*/ {

  protected var name: String = _
  protected var url: String = _
  protected var protocol: String = _
  protected var method: String = _

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

  // injectable, change to better name
  // def runRequest(): String = {

  //
  // }

}
