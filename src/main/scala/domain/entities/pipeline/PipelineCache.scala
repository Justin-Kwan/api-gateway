package pipeline

import scala.collection.mutable

// static object that can be referenced in request handlers
final object PipelineCache {

  final private val pipelines = mutable.Map[String, Pipeline]()

  def getPipelineInstance(pipelineKey: String): Unit = {


    // check if key exists
    // if() {
    //
    // }

  }




}
