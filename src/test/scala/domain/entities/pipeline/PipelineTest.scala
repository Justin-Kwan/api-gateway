import scala.collection.immutable.Vector

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter
import org.scalatest.PrivateMethodTester
import org.scalatest.Matchers

import endpointstages.Policy
import endpointstages.Service
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

  def getMockPolicy(name: String, url: String, protocol: String, method: String, successCondition: String): Policy = {
    val mockPolicy = new Policy()
    mockPolicy.setName(name)
    mockPolicy.setUrl(url)
    mockPolicy.setProtocol(protocol)
    mockPolicy.setMethod(method)
    mockPolicy.setSuccessCondition(successCondition)
    return mockPolicy
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

  describe("addEndpoint() tests") {
    it("should add 1 endpoint, a mock policy") {
      val mockPolicy1 = this.getMockPolicy(
        "mock policy 1",
        "mockpolicyurl1.com",
        "mock policy protocol 1",
        "mock policy method 1",
        "mock sucess condition 1"
      )

      val mockEndpointStages = Vector(mockPolicy1)
      pipeline.addEndpoint(mockPolicy1)
      pipeline.getEndpoints() should be (mockEndpointStages)
    }

    it("should add 1 endpoint, a mock service") {
      val mockService1 = this.getMockService(
        "mock service 1",
        "mockserviceurl1.com",
        "mock service protocol 1",
        "mock service method 1"
      )

      val mockEndpointStages = Vector(mockService1)
      pipeline.addEndpoint(mockService1)
      pipeline.getEndpoints() should be (mockEndpointStages)
    }

    it("should add 2 endpoints, a mock policy and a mock service") {
      val mockPolicy1 = this.getMockPolicy(
        "mock policy 1",
        "mockpolicyurl1.com",
        "mock policy protocol 1",
        "mock policy method 1",
        "mock sucess condition 1"
      )

      val mockService1 = this.getMockService(
        "mock service 1",
        "mockserviceurl1.com",
        "mock service protocol 1",
        "mock service method 1"
      )

      val mockEndpointStages = Vector(mockPolicy1, mockService1)
      pipeline.addEndpoint(mockPolicy1)
      pipeline.addEndpoint(mockService1)
      pipeline.getEndpoints() should be (mockEndpointStages)
    }
  }

  describe("getEndpoints() tests") {
    it("should get no endpoints (empty)") {
      val mockEndpointStages = Vector()
      pipeline.getEndpoints() should be (mockEndpointStages)
    }

    it("should get 5 endpoints in correct order") {
      val mockPolicy1 = this.getMockPolicy(
        "mock policy 1",
        "mockpolicyurl1.com",
        "mock policy protocol 1",
        "mock policy method 1",
        "mock sucess condition 1"
      )

      val mockPolicy2 = this.getMockPolicy(
        "mock policy 2",
        "mockpolicyurl2.com",
        "mock policy protocol 2",
        "mock policy method 2",
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

      val mockEndpointStages = Vector(mockPolicy1, mockService1, mockPolicy2, mockService2, mockService3)
      pipeline.addEndpoint(mockPolicy1)
      pipeline.addEndpoint(mockService1)
      pipeline.addEndpoint(mockPolicy2)
      pipeline.addEndpoint(mockService2)
      pipeline.addEndpoint(mockService3)
      pipeline.getEndpoints() should be (mockEndpointStages)
    }
  }

}
