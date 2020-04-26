package testutilities

import networking.HttpRequestClient
import requeststages.MiddlewareRequest
import requeststages.ServiceRequest
import pipeline.Pipeline

/**
 * Generates and provides mock objects used as dependencies for
 * unit/integration tests
 */
final object ObjectMother {

   /**
    * function for generating mock MiddlewareRequest objects
    */
   def getMockMiddlewareRequest(name: String, url: String, protocol: String, method: String, body: String, successResponse: String): MiddlewareRequest = {
     val mockMiddlewareRequest: MiddlewareRequest = new MiddlewareRequest()
     mockMiddlewareRequest.setName(name)
     mockMiddlewareRequest.setUrl(url)
     mockMiddlewareRequest.setProtocol(protocol)
     mockMiddlewareRequest.setMethod(method)
     mockMiddlewareRequest.setBody(body)
     mockMiddlewareRequest.setSuccessResponse(successResponse)
     mockMiddlewareRequest.setRequestFunc(HttpRequestClient.getRequestFunc(method))
     return mockMiddlewareRequest
   }

   /**
    * function for generating mock ServiceRequest objects
    */
   def getMockServiceRequest(name: String, url: String, protocol: String, method: String, body: String): ServiceRequest = {
     val mockServiceRequest: ServiceRequest = new ServiceRequest()
     mockServiceRequest.setName(name)
     mockServiceRequest.setUrl(url)
     mockServiceRequest.setProtocol(protocol)
     mockServiceRequest.setMethod(method)
     mockServiceRequest.setBody(body)
     mockServiceRequest.setRequestFunc(HttpRequestClient.getRequestFunc(method))
     return mockServiceRequest
   }

   /**
    * function for generating mock Pipeline objects
    */
   def getMockPipeline(pipelineName: String, serviceRequests: Vector[ServiceRequest]): Pipeline = {
     val pipeline: Pipeline = new Pipeline(pipelineName)
     for(i <- 0 to serviceRequests.length - 1) {
       pipeline.pipeServiceRequest(serviceRequests(i))
     }
     return pipeline
   }



}
