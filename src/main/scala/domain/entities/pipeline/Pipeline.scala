package pipeline

import scala.collection.immutable.Vector
import scala.collection.mutable.ArrayBuffer

import endpointstages.Endpoint
import endpointstages.Service
import endpointstages.Policy

final class Pipeline {

  private var name: String      = _
  private var endpoints         = Vector[Endpoint]()
  private var responseAggregate = ArrayBuffer[String]()

  def this(name: String) = {
    this()
    this.setName(name)
  }

  private def setName(name: String): Unit = {
    this.name = name
  }

  def getName(): String = {
    return this.name
  }

  def addEndpoint(endpoint: Endpoint): Unit = {
    this.endpoints = this.endpoints :+ endpoint
  }

  def getEndpoints(): Vector[Endpoint] = {
    return this.endpoints
  }

}
