
class RequestContext {

  def selectRequestStrategy(requestProtocol: String) {

    val month = requestProtocol match {
      case "get"  =>
      case "get"  => "February"
      case _  => "Invalid month"
    }

  }

}
