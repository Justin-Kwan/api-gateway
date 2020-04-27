package sharedutilities

import scala.collection.immutable.Vector

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonArray

import requeststages.ServiceRequest
import requeststages.MiddlewareRequest


final object JsonObjectMapper {

  private val gson: Gson = new Gson()

  def mapServiceRequests(serviceRequestsJson: JsonArray, middlewareRequestsJson: JsonArray): Vector[ServiceRequest] = {
    var serviceRequests = Vector[ServiceRequest]()

    for(i <- 0 until serviceRequestsJson.size()) {
      val serviceRequestJson: JsonObject = serviceRequestsJson.get(i).getAsJsonObject()

      var serviceRequest: ServiceRequest = gson.fromJson(
        serviceRequestJson.toString(),
        classOf[ServiceRequest]
      )
      serviceRequests = serviceRequests :+ serviceRequest
    }

    return serviceRequests
  }

}
