package endpointstages

class Endpoint {
  protected var name: String = _
  protected var url: String = _
  protected var protocol: String = _
  protected var method: String = _

  def setName(name: String): Unit = {
    this.name = name
  }

  def getName(): String = {
    return this.name
  }

  def setUrl(url: String): Unit = {
    this.url = url
  }

  def getUrl(): String = {
    return this.url
  }

  def setProtocol(protocol: String): Unit = {
    this.protocol = protocol
  }

  def getProtocol(): String = {
    return this.protocol
  }

  def setMethod(method: String): Unit = {
    this.method = method
  }

  def getMethod(): String = {
    return this.method
  }

}
