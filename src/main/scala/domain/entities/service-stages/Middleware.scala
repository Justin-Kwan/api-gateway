package servicestages

import servicestages.Service

final class Middleware extends Service {

  private var successResponse: String = _

  def setSuccessResponse(successResponse: String): Unit = {
    this.successResponse = successResponse
  }

  def getSuccessResponse(): String = {
    return this.successResponse
  }
  
  override def runRequest(): String = {

  }

}
