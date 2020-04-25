package requeststages

import requeststages.ServiceRequest

final class MiddlewareRequest extends ServiceRequest {

  private var successResponse: String = _

  def setSuccessResponse(successResponse: String): Unit = {
    this.successResponse = successResponse
  }

  def getSuccessResponse(): String = {
    return this.successResponse
  }

  // override def runRequest(): String = {
  //
  // }

}
