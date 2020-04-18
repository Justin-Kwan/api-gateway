package pipeline

import scala.collection.mutable

import spray.json._
import spray.json.DefaultJsonProtocol._

final class PipelineFactory {


  private def createPipeline() {

  }

  // returns a map of pipelines
  def createPipelines(gatewayConfigJson: String): mutable.Map[String, Pipeline] = {
    var pipelines = mutable.Map[String, Pipeline]()

    val jsonAst = JsonParser(gatewayConfigJson)


    // calls parser

    return pipelines
  }

}
