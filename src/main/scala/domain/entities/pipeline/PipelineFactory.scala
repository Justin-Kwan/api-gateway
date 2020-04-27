package pipeline

import scala.collection.mutable

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser

import sharedutilities.JsonObjectMapper
import pipeline.Pipeline
import requeststages.MiddlewareRequest
import requeststages.ServiceRequest
import networking.HttpRequestClient


final class PipelineFactory {

  private val gson: Gson = new Gson()
  private val parser: JsonParser = new JsonParser()


  def injectRequestFunc(pipeline: Pipeline, serviceRequests: Vector[ServiceRequest]): Pipeline = {

    for(i <- 0 until serviceRequests.length) {
      val requestFunc: (String, String) => String =
        HttpRequestClient.getRequestFunc(serviceRequests(i).getMethod())
      serviceRequests(i).setRequestFunc(requestFunc)
    }

    pipeline.pipeServiceRequests(serviceRequests)
    return pipeline
  }

  private def getPipeline(pipelineJson: JsonObject): Pipeline = {
    val pipelineName: String              = pipelineJson.get("name").getAsString();
    val middlewareRequestsJson: JsonArray = pipelineJson.getAsJsonArray("middleware requests")
    val serviceRequestsJson: JsonArray    = pipelineJson.getAsJsonArray("service requests")
    val middlewareRequestCount: Int       = middlewareRequestsJson.size()

    var pipeline: Pipeline = new Pipeline(pipelineName)

    val middlewareRequests: Vector[MiddlewareRequest] = JsonObjectMapper.mapServiceRequests(middlewareRequestsJson)
    val serviceRequests: Vector[ServiceRequest] = JsonObjectMapper.mapServiceRequests(serviceRequestsJson)

    val srs: Vector [ServiceRequest] = middlewareRequests ++ serviceRequests


    pipeline = injectRequestFunc(pipeline, srs)

    return pipeline
  }

  // @throws(classOf[])
  def createPipelines(gatewayConfigJson: String): mutable.Map[String, Pipeline] = {
    val pipelinesJsonObj: JsonObject = parser.parse(gatewayConfigJson).getAsJsonObject()
    val pipelinesJson: JsonArray     = pipelinesJsonObj.getAsJsonArray("pipelines");
    val pipelineCount: Int           = pipelinesJson.size()

    var pipelines = mutable.Map[String, Pipeline]()

    for(i <- 0 until pipelineCount) {
      val pipelineJson: JsonObject = pipelinesJson.get(i).getAsJsonObject()
      val pipeline: Pipeline = this.getPipeline(pipelineJson)
      pipelines += (pipeline.getName() -> pipeline)
		}

    return pipelines
  }

}
