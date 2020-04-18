package sharedutilities

import scala.io.Source
import java.io._

final object JsonReader {

  final val filePaths = Map(
    "GATEWAY_CONFIG" -> "configs/gateway-config.json"
  )

  def openFile(fileKey: String): String = {
    val file = new File(
      getClass
      .getClassLoader
      .getResource(filePaths(fileKey))
      .getPath
    )

    val gatewayConfigJson = Source.fromFile(file).mkString
    return gatewayConfigJson
  }

}
