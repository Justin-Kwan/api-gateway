import scala.collection.immutable.Vector

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter
import org.scalatest.PrivateMethodTester
import org.scalatest.Matchers

import testutilities.ObjectMother
import testutilities.ObjectMatcher

import requeststages.MiddlewareRequest
import requeststages.ServiceRequest
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

  describe("constructor() test") {
    it("should set the name on instantiation (a long string)") {
      var mockPipeline: Pipeline = new Pipeline(LongMockString)
      assert(mockPipeline.getName() == LongMockString)
    }
  }

  describe("pipeServiceRequest() tests") {
    it("should add a mock middleware request") {
      val mockMiddlewareRequest1 = ObjectMother.getMockMiddlewareRequest(
        "mock middleware 1",
        "mockmiddlewareurl1.com",
        "mock middleware protocol 1",
        "get",
        "mock body 1",
        "mock sucess condition 1"
      )

      val mockServiceRequests = Vector(mockMiddlewareRequest1)
      pipeline.pipeServiceRequest(mockMiddlewareRequest1)
      assert(ObjectMatcher.areObjectsEqual(
        pipeline.getServiceRequests(),
        mockServiceRequests)
      )
    }

    it("should add a mock service request") {
      val mockServiceRequest1 = ObjectMother.getMockServiceRequest(
        "mock service 1",
        "mockserviceurl1.com",
        "mock service protocol 1",
        "get",
        "mock body 1"
      )

      val mockServiceRequests = Vector(mockServiceRequest1)
      pipeline.pipeServiceRequest(mockServiceRequest1)
      assert(ObjectMatcher.areObjectsEqual(
        pipeline.getServiceRequests(),
        mockServiceRequests)
      )
    }

    it("should add a mock middleware request and a mock service request") {
      val mockMiddlewareRequest1 = ObjectMother.getMockMiddlewareRequest(
        "mock middleware 1",
        "mockmiddlewareurl1.com",
        "mock middleware protocol 1",
        "post",
        "mock body 1",
        "mock sucess condition 1"
      )

      val mockServiceRequest1 = ObjectMother.getMockServiceRequest(
        "mock service 1",
        "mockserviceurl1.com",
        "mock service protocol 1",
        "post",
        "mock body 1"
      )

      val mockServiceRequests = Vector(mockMiddlewareRequest1, mockServiceRequest1)
      pipeline.pipeServiceRequest(mockMiddlewareRequest1)
      pipeline.pipeServiceRequest(mockServiceRequest1)
      assert(ObjectMatcher.areObjectsEqual(
        pipeline.getServiceRequests(),
        mockServiceRequests)
      )
    }
  }

  describe("getServiceRequests() tests") {
    it("should get no service requests (empty)") {
      val mockServiceRequests = Vector()
      pipeline.getServiceRequests() should be (mockServiceRequests)
    }

    it("should get 5 service requests in correct order") {
      val mockMiddlewareRequest1 = ObjectMother.getMockMiddlewareRequest(
        "mock middleware 1",
        "mockmiddlewareurl1.com",
        "mock middleware protocol 1",
        "post",
        "mock body 1",
        "mock sucess condition 1"
      )

      val mockMiddlewareRequest2 = ObjectMother.getMockMiddlewareRequest(
        "mock middleware 2",
        "mockmiddlewareurl2.com",
        "mock middleware protocol 2",
        "post",
        "mock body 2",
        "mock sucess condition 2"
      )

      val mockServiceRequest1 = ObjectMother.getMockServiceRequest(
        "mock service 1",
        "mockserviceurl1.com",
        "mock service protocol 1",
        "post",
        "mock body 3"
      )

      val mockServiceRequest2 = ObjectMother.getMockServiceRequest(
        "mock service 2",
        "mockserviceurl2.com",
        "mock service protocol 2",
        "post",
        "mock body 3"
      )

      val mockServiceRequest3 = ObjectMother.getMockServiceRequest(
        "mock service 3",
        "mockserviceurl3.com",
        "mock service protocol 3",
        "post",
        "mock body 3"
      )

      val mockServiceRequests = Vector(mockMiddlewareRequest1, mockServiceRequest1, mockMiddlewareRequest2, mockServiceRequest2, mockServiceRequest3)
      pipeline.pipeServiceRequest(mockMiddlewareRequest1)
      pipeline.pipeServiceRequest(mockServiceRequest1)
      pipeline.pipeServiceRequest(mockMiddlewareRequest2)
      pipeline.pipeServiceRequest(mockServiceRequest2)
      pipeline.pipeServiceRequest(mockServiceRequest3)
      assert(ObjectMatcher.areObjectsEqual(
        pipeline.getServiceRequests(),
        mockServiceRequests)
      )
    }
  }

}
