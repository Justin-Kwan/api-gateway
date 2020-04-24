package pipeline

import scala.collection.mutable

import com.google.gson.Gson
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import pipeline.Pipeline
import servicestages.Middleware
import servicestages.Service


final class PipelineFactory {

  private val gson: Gson = new Gson()
  private val parser: JsonParser = new JsonParser();

  private def getPipeline(pipelineJson: JsonObject): Pipeline = {
    val pipelineName: String       = pipelineJson.get("name").getAsString();
    val middlewaresJson: JsonArray = pipelineJson.getAsJsonArray("middlewares")
    val servicesJson: JsonArray    = pipelineJson.getAsJsonArray("services")
    val middlewareCount: Int       = middlewaresJson.size()
    val serviceCount: Int          = servicesJson.size()

    val pipeline: Pipeline = new Pipeline(pipelineName)

    for(i <- 0 to middlewareCount) {
      val middlewareJson: JsonObject = middlewaresJson.get(i).getAsJsonObject()

      var middleware: Middleware = gson.fromJson(
        middlewareJson.toString(),
        classOf[Middleware]
      )

      requestFunction = getRequestFunction(middleware.getMethod())
      middleware.setRequestFunction(requestFunction)
      pipeline.pipeService(middleware)
    }

    for(i <- 0 to serviceCount) {
      val serviceJson: JsonObject = servicesJson.get(i).getAsJsonObject()
      var service: Service = gson.fromJson(
        serviceJson.toString(),
        classOf[Service]
      )

      requestFunction = getRequestFunction(service.getMethod())
      middleware.setRequestFunction(requestFunction)
      pipeline.pipeService(service)
    }

    return pipeline
  }

  // returns a map of pipelines
  def createPipelines(gatewayConfigJson: String): mutable.Map[String, Pipeline] = {
    val pipelinesJsonObj: JsonObject = parser.parse(gatewayConfigJson).getAsJsonObject()
    val pipelinesJson: JsonArray     = pipelinesJsonObj.getAsJsonArray("pipelines");
    val pipelineCount: Int           = pipelinesJson.size()

    var pipelines = mutable.Map[String, Pipeline]()

    for(i <- 0 to pipelineCount) {
      val pipelineJson: JsonObject = pipelinesJson.get(i).getAsJsonObject()
      val pipeline: Pipeline = this.getPipeline(pipelineJson)
      this.pipelines += (pipeline.getName() -> pipeline)
		}

    return pipelines
  }

}
