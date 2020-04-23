import scala.collection.immutable.Vector

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter
import org.scalatest.PrivateMethodTester
import org.scalatest.Matchers

import servicestages.Middleware
import servicestages.Service
import pipeline.Pipeline

final class PipelineTest extends FunSpec with BeforeAndAfter with PrivateMethodTester with Matchers {

  final val LongMockString = """Lorem ipsum dolor sit amet, consectetuer adipiscing
  elit. Aenean commodo ldawdawawdawd)*(&^igula eget dolor. Ais natoque
  penatibus et magnis dis parturient awdawdmontes, nascetur ridiculus mus. Donec quam
  felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa
  quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu.
  In enim justo, rhoncus ut, imperdiet a, venenatiwdaads vitae, justo. Nullam dictum
  felis eu pede mollis wpretium. Integer tincidunt. Cras dapibus. Vivamus elementum
  semper nisi. Aenean vulputate eleifednd tellus. Aenean leo ligula, porttitor eu,
  semper nisi. Aenean vulputate eleifedGP*(Ynd tellus. Aenean leo ligula, porttitor eu,
  consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis,
  feugiat a, tellus. Pdawdhasellus viverra nulla ut metus varius laoreet. Quisque rutrum.
  Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies
  nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum
  rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam
  quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio
  et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam
  quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla
  mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget
  bibendum sodales, augue velit cur123sus nunc,"""

  var pipeline: Pipeline = _

  before {
    pipeline = new Pipeline()
  }

  def getMockMiddleware(name: String, url: String, protocol: String, method: String, successResponse: String): Middleware = {
    val mockMiddleware = new Middleware()
    mockMiddleware.setName(name)
    mockMiddleware.setUrl(url)
    mockMiddleware.setProtocol(protocol)
    mockMiddleware.setMethod(method)
    mockMiddleware.setSuccessResponse(successResponse)
    return mockMiddleware
  }

  def getMockService(name: String, url: String, protocol: String, method: String): Service = {
    val mockService = new Service()
    mockService.setName(name)
    mockService.setUrl(url)
    mockService.setProtocol(protocol)
    mockService.setMethod(method)
    return mockService
  }

  describe("constructor() test") {
    it("should set the name on instantiation (a long string)") {
      var mockPipeline: Pipeline = new Pipeline(LongMockString)
      assert(mockPipeline.getName() == LongMockString)
    }
  }

  describe("setName() / getName() test") {
    it("should set and get the name (a long string)") {
      val setName = PrivateMethod[String]('setName)
      pipeline invokePrivate setName(LongMockString)
      assert(pipeline.getName() == LongMockString)
    }
  }

  describe("pipeService() tests") {
    it("should add 1 service, a mock middleware") {
      val mockMiddleware1 = this.getMockMiddleware(
        "mock middleware 1",
        "mockmiddlewareurl1.com",
        "mock middleware protocol 1",
        "mock middleware method 1",
        "mock sucess condition 1"
      )

      val mockServiceStages = Vector(mockMiddleware1)
      pipeline.pipeService(mockMiddleware1)
      pipeline.getServices() should be (mockServiceStages)
    }

    it("should add 1 service, a mock service") {
      val mockService1 = this.getMockService(
        "mock service 1",
        "mockserviceurl1.com",
        "mock service protocol 1",
        "mock service method 1"
      )

      val mockServiceStages = Vector(mockService1)
      pipeline.pipeService(mockService1)
      pipeline.getServices() should be (mockServiceStages)
    }

    it("should add 2 services, a mock middleware and a mock service") {
      val mockMiddleware1 = this.getMockMiddleware(
        "mock middleware 1",
        "mockmiddlewareurl1.com",
        "mock middleware protocol 1",
        "mock middleware method 1",
        "mock sucess condition 1"
      )

      val mockService1 = this.getMockService(
        "mock service 1",
        "mockserviceurl1.com",
        "mock service protocol 1",
        "mock service method 1"
      )

      val mockServiceStages = Vector(mockMiddleware1, mockService1)
      pipeline.pipeService(mockMiddleware1)
      pipeline.pipeService(mockService1)
      pipeline.getServices() should be (mockServiceStages)
    }
  }

  describe("getServices() tests") {
    it("should get no services (empty)") {
      val mockServiceStages = Vector()
      pipeline.getServices() should be (mockServiceStages)
    }

    it("should get 5 services in correct order") {
      val mockMiddleware1 = this.getMockMiddleware(
        "mock middleware 1",
        "mockmiddlewareurl1.com",
        "mock middleware protocol 1",
        "mock middleware method 1",
        "mock sucess condition 1"
      )

      val mockMiddleware2 = this.getMockMiddleware(
        "mock middleware 2",
        "mockmiddlewareurl2.com",
        "mock middleware protocol 2",
        "mock middleware method 2",
        "mock sucess condition 2"
      )

      val mockService1 = this.getMockService(
        "mock service 1",
        "mockserviceurl1.com",
        "mock service protocol 1",
        "mock service method 1"
      )

      val mockService2 = this.getMockService(
        "mock service 2",
        "mockserviceurl2.com",
        "mock service protocol 2",
        "mock service method 2"
      )

      val mockService3 = this.getMockService(
        "mock service 3",
        "mockserviceurl3.com",
        "mock service protocol 3",
        "mock service method 3"
      )

      val mockServiceStages = Vector(mockMiddleware1, mockService1, mockMiddleware2, mockService2, mockService3)
      pipeline.pipeService(mockMiddleware1)
      pipeline.pipeService(mockService1)
      pipeline.pipeService(mockMiddleware2)
      pipeline.pipeService(mockService2)
      pipeline.pipeService(mockService3)
      pipeline.getServices() should be (mockServiceStages)
    }
  }

}
