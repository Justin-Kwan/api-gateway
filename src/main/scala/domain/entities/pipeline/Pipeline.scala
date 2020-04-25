package pipeline

import scala.collection.immutable.Vector
import scala.collection.mutable.ArrayBuffer

import requeststages.ServiceRequest
import requeststages.MiddlewareRequest

final class Pipeline {

  private var name: String      = _
  private var serviceRequests   = Vector[ServiceRequest]()
  private var responseAggregate = ArrayBuffer[String]()

  def this(name: String) = {
    this()
    this.setName(name)
  }

  private def setName(name: String): Unit = {
    this.name = name
  }

  def pipeServiceRequest(serviceRequest: ServiceRequest): Unit = {
    this.serviceRequests = this.serviceRequests :+ serviceRequest
  }

  def getName(): String = {
    return this.name
  }

  def getServiceRequests(): Vector[ServiceRequest] = {
    return this.serviceRequests
  }

  // def execute(): Unit = {
  //   for(i <- 0 to serviceRequests.length) {
  //     // either build request here on earlier
  //     requestParam = requestBuilder.buildRequestParams(serviceRequest, requestParamMap) // can use a try catch on map
  //     serviceRequests[i].runRequest(requestParam)
  //   }
  // }

}
