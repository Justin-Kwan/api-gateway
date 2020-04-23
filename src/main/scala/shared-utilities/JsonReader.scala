package sharedutilities

import java.io._
import scala.io.Source

final object JsonReader {

  private val filePaths = Map(
    "GATEWAY_CONFIG" -> "configs/gateway-config.json"
  )

  def openFile(fileKey: String): String = {
    val file = new File(getClass
      .getClassLoader
      .getResource(filePaths(fileKey))
      .getPath
    )

    val gatewayConfigJson = Source.fromFile(file).mkString
    return gatewayConfigJson
  }

}
