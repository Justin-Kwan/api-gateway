package pipeline

import scala.collection.mutable

import com.google.gson.Gson
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import pipeline.Pipeline
import requeststages.MiddlewareRequest
import requeststages.ServiceRequest
import networking.HttpRequestClient


final class PipelineFactory {

  private val gson: Gson = new Gson()
  private val parser: JsonParser = new JsonParser();

  private def getPipeline(pipelineJson: JsonObject): Pipeline = {
    val pipelineName: String       = pipelineJson.get("name").getAsString();
    val middlewareRequestsJson: JsonArray = pipelineJson.getAsJsonArray("middleware requests")
    val serviceRequestsJson: JsonArray    = pipelineJson.getAsJsonArray("service requests")
    val middlewareRequestCount: Int       = middlewareRequestsJson.size()
    val serviceRequestCount: Int          = serviceRequestsJson.size()

    val pipeline: Pipeline = new Pipeline(pipelineName)

    for(i <- 0 until middlewareRequestCount) {
      val middlewareRequestJson: JsonObject = middlewareRequestsJson.get(i).getAsJsonObject()

      var middlewareRequest: MiddlewareRequest = gson.fromJson(
        middlewareRequestJson.toString(),
        classOf[MiddlewareRequest]
      )

      val requestFunc: (String, String) => String = HttpRequestClient.getRequestFunc(middlewareRequest.getMethod())
      middlewareRequest.setRequestFunc(requestFunc)
      pipeline.pipeServiceRequest(middlewareRequest)
    }

    for(i <- 0 until serviceRequestCount) {
      val serviceRequestJson: JsonObject = serviceRequestsJson.get(i).getAsJsonObject()
      var serviceRequest: ServiceRequest = gson.fromJson(
        serviceRequestJson.toString(),
        classOf[ServiceRequest]
      )

      val requestFunc: (String, String) => String = HttpRequestClient.getRequestFunc(serviceRequest.getMethod())
      serviceRequest.setRequestFunc(requestFunc)
      pipeline.pipeServiceRequest(serviceRequest)
    }

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
