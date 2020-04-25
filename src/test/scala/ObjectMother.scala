package test

final object ObjectMother {

  /**
   * functions for generating mock pipelines and services
   */

   def getMockMiddlewareRequest(name: String, url: String, protocol: String, method: String, body: String, successResponse: String): MiddlewareRequest = {
     val mockMiddlewareRequest: MiddlewareRequest = new MiddlewareRequest()
     mockMiddlewareRequest.setName(name)
     mockMiddlewareRequest.setUrl(url)
     mockMiddlewareRequest.setProtocol(protocol)
     mockMiddlewareRequest.setMethod(method)
     mockMiddlewareRequest.setBody(body)
     mockMiddlewareRequest.setSuccessResponse(successResponse)
     return mockMiddlewareRequest
   }

   def getMockServiceRequest(name: String, url: String, protocol: String, method: String, body: String): ServiceRequest = {
     val mockServiceRequest: ServiceRequest = new ServiceRequest()
     mockServiceRequest.setName(name)
     mockServiceRequest.setUrl(url)
     mockServiceRequest.setProtocol(protocol)
     mockServiceRequest.setMethod(method)
     mockServiceRequest.setBody(body)
     return mockServiceRequest
   }

   def getMockPipeline(pipelineName: String, serviceRequests: Vector[ServiceRequest]): Pipeline = {
     val pipeline: Pipeline = new Pipeline(pipelineName)
     for(i <- 0 to serviceRequests.length - 1) {
       pipeline.pipeServiceRequest(serviceRequests(i))
     }
     return pipeline
   }



}
