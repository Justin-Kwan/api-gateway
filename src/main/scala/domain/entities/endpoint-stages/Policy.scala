package endpointstages

import endpointstages.Endpoint

final class Policy extends Endpoint {

  private var successCondition: String = _

  def setSuccessCondition(successCondition: String): Unit = {
    this.successCondition = successCondition
  }

  def getSuccessCondition(): String = {
    return this.successCondition
  }

}
