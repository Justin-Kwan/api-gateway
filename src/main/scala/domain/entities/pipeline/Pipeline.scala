package pipeline

import scala.collection.immutable.Vector
import scala.collection.mutable.ArrayBuffer

import servicestages.Service
import servicestages.Middleware

final class Pipeline {

  private var name: String      = _
  private var services          = Vector[Service]()
  private var responseAggregate = ArrayBuffer[String]()

  def this(name: String) = {
    this()
    this.setName(name)
  }

  private def setName(name: String): Unit = {
    this.name = name
  }

  def pipeService(service: Service): Unit = {
    this.services = this.services :+ service
  }

  def getName(): String = {
    return this.name
  }

  def getServices(): Vector[Service] = {
    return this.services
  }

  def execute(): Unit = {

    for(i <- 0 to services.length) {
      // either build request here on earlier
      requestParam = requestBuilder.buildRequestParams(service, requestParamMap) // can use a try catch on map
      services[i].runRequest(requestParam)
    }

  }

}
