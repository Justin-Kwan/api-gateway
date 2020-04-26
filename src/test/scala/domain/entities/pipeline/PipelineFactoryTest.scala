import scala.collection.immutable.Vector
import scala.collection.mutable

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfterEach
import org.scalatest.PrivateMethodTester
import org.scalatest.Matchers

import testutilities.ObjectMother
import testutilities.ObjectMatcher
import pipelinetests.MockPipelineJson

import requeststages.MiddlewareRequest
import requeststages.ServiceRequest
import pipeline.Pipeline
import pipeline.PipelineFactory

final class PipelineFactoryTest
      extends FunSpec
      with BeforeAndAfterEach
      with PrivateMethodTester
      with Matchers {

  private val pipelineFactory: PipelineFactory = new PipelineFactory()

  describe("createPipelines() test") {
    it("should create a map of 0 pipelines") {
      val pipelines =
        pipelineFactory
        .createPipelines(MockPipelineJson.mockPipelineJson0)
      assert(pipelines.isEmpty)
    }

    it("should create a map of 1 pipeline (holding 0 middleware requests and 0 service requests)") {
      val mockPipeline: Pipeline =
        ObjectMother.getMockPipeline(
        "pipeline empty requests",
        Vector()
      )
      // function under test
      val mockPipelines =
        pipelineFactory
        .createPipelines(MockPipelineJson.mockPipelineJson1)

      assert(ObjectMatcher.areObjectsEqual(
        mockPipeline,
        mockPipelines("pipeline empty requests"))
      )
      mockPipelines("pipeline empty requests") shouldBe a [Pipeline]
      assert(mockPipelines.size == 1)
    }

    it("should create a map of 1 pipeline (holding 1 middleware requests and 1 service requests)") {
      val mockMiddlewareRequest1: MiddlewareRequest =
        ObjectMother.getMockMiddlewareRequest(
        "middleware1",
        "middleware1.com",
        "rest",
        "post",
        "request body",
        "user authorized"
      )
      val mockServiceRequest1: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "service1",
        "service1.com",
        "rest",
        "get",
        "request body"
      )
      val mockPipeline: Pipeline = ObjectMother.getMockPipeline(
        "pipeline1",
        Vector(
          mockMiddlewareRequest1,
          mockServiceRequest1
        )
      )
      // function under test
      val mockPipelines =
        pipelineFactory
        .createPipelines(MockPipelineJson.mockPipelineJson2)

      assert(ObjectMatcher.areObjectsEqual(mockPipeline, mockPipelines("pipeline1")))
      mockPipelines("pipeline1") shouldBe a [Pipeline]
      assert(mockPipelines.size == 1)
    }

    it("should create a map of 1 pipeline (holding 1 service request)") {
      val mockServiceRequest1: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "service1",
        "service1.com",
        "rest",
        "get",
        "request body"
      )
      val mockPipeline: Pipeline = ObjectMother.getMockPipeline(
        "pipeline1",
        Vector(mockServiceRequest1)
      )
      // function under test
      val mockPipelines =
        pipelineFactory
        .createPipelines(MockPipelineJson.mockPipelineJson3)

      assert(ObjectMatcher.areObjectsEqual(
        mockPipeline,
        mockPipelines("pipeline1"))
      )
      mockPipelines("pipeline1") shouldBe a [Pipeline]
      assert(mockPipelines.size == 1)
    }

    it("should create a map of 1 pipeline (holding 1 middleware request)") {
      val mockMiddlewareRequest1: MiddlewareRequest =
        ObjectMother.getMockMiddlewareRequest(
        "middleware1",
        "middleware1.com",
        "rest",
        "post",
        "request body",
        "user authorized"
      )
      val mockPipeline: Pipeline = ObjectMother.getMockPipeline(
        "pipeline1",
        Vector(mockMiddlewareRequest1)
      )
      // function under test
      val mockPipelines =
        pipelineFactory
        .createPipelines(MockPipelineJson.mockPipelineJson4)

      assert(ObjectMatcher.areObjectsEqual(
        mockPipeline,
        mockPipelines("pipeline1"))
      )
      mockPipelines("pipeline1") shouldBe a [Pipeline]
      assert(mockPipelines.size == 1)
    }

    it("should create a map of 1 pipeline (holding 2 middleware requests and 3 service requests)") {
      val mockMiddlewareRequest1: MiddlewareRequest =
        ObjectMother.getMockMiddlewareRequest(
        "middleware1",
        "middleware1.com",
        "rest",
        "post",
        "request body",
        "user authorized"
      )
      val mockMiddlewareRequest2: MiddlewareRequest =
        ObjectMother.getMockMiddlewareRequest(
        "middleware2",
        "middleware2.com",
        "rest",
        "delete",
        "request body",
        "user authorized"
      )
      val mockServiceRequest1: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "service1",
        "service1.com",
        "rest",
        "patch",
        "request body"
      )
      val mockServiceRequest2: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "service2",
        "service2.com",
        "rest",
        "put",
        "request body"
      )
      val mockServiceRequest3: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "service3",
        "service3.com",
        "rest",
        "delete",
        "request body"
      )

      val mockPipeline: Pipeline = ObjectMother.getMockPipeline(
        "pipeline with many services",
        Vector(
          mockMiddlewareRequest1,
          mockMiddlewareRequest2,
          mockServiceRequest1,
          mockServiceRequest2,
          mockServiceRequest3
        )
      )
      // function under test
      val mockPipelines =
        pipelineFactory
        .createPipelines(MockPipelineJson.mockPipelineJson5)

      assert(ObjectMatcher.areObjectsEqual(
        mockPipeline,
        mockPipelines("pipeline with many services"))
      )
      mockPipelines("pipeline with many services") shouldBe a [Pipeline]
      assert(mockPipelines.size == 1)
    }

    it("""should create a map of 3 pipelines (pipeline 1 with 2 middleware
      requests and 3 service requests, pipeline 2 with 4 middleware requests
      and 3 service requests, and pipeline 3 with 4 middleware requests and
      5 service requests)"""
    ) {

      //////////////////////////////////////////////////////////////////////////
      // Pipeline 1 service and middleware requests
      //////////////////////////////////////////////////////////////////////////
      val mockPipeline1MiddlewareRequest1: MiddlewareRequest =
        ObjectMother.getMockMiddlewareRequest(
        "pipeline 1 middleware1",
        "middleware1.com",
        "rest",
        "post",
        "pipeline 1 request body",
        "user authorized"
      )

      val mockPipeline1MiddlewareRequest2: MiddlewareRequest =
        ObjectMother.getMockMiddlewareRequest(
        "pipeline 1 middleware2",
        "middleware2.com",
        "rest",
        "delete",
        "pipeline 1 request body",
        "user authorized"
      )

      val mockPipeline1ServiceRequest1: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "pipeline 1 service1",
        "service1.com",
        "rest",
        "patch",
        "pipeline 1 request body"
      )

      val mockPipeline1ServiceRequest2: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "pipeline 1 service2",
        "service2.com",
        "rest",
        "put",
        "pipeline 1 request body"
      )

      val mockPipeline1ServiceRequest3: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "pipeline 1 service3",
        "service3.com",
        "rest",
        "delete",
        "pipeline 1 request body"
      )

      //////////////////////////////////////////////////////////////////////////
      // Pipeline 2 service and middleware requests
      //////////////////////////////////////////////////////////////////////////
      val mockPipeline2MiddlewareRequest1: MiddlewareRequest =
        ObjectMother.getMockMiddlewareRequest(
        "pipeline 2 middleware1",
        "middleware1.com",
        "rest",
        "post",
        "pipeline 2 request body",
        "user authorized"
      )

      val mockPipeline2MiddlewareRequest2: MiddlewareRequest =
        ObjectMother.getMockMiddlewareRequest(
        "pipeline 2 middleware2",
        "middleware2.com",
        "rest",
        "delete",
        "pipeline 2 request body",
        "user authorized"
      )

      val mockPipeline2MiddlewareRequest3: MiddlewareRequest =
        ObjectMother.getMockMiddlewareRequest(
        "pipeline 2 middleware3",
        "middleware3.com",
        "rest",
        "patch",
        "pipeline 2 request body",
        "user authorized"
      )

      val mockPipeline2MiddlewareRequest4: MiddlewareRequest =
        ObjectMother.getMockMiddlewareRequest(
        "pipeline 2 middleware4",
        "middleware4.com",
        "rest",
        "put",
        "pipeline 2 request body",
        "rate limit not exceeded"
      )

      val mockPipeline2ServiceRequest1: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "pipeline 2 service1",
        "service1.com",
        "rest",
        "patch",
        "pipeline 2 request body"
      )

      val mockPipeline2ServiceRequest2: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "pipeline 2 service2",
        "service2.com",
        "rest",
        "put",
        "pipeline 2 request body"
      )

      val mockPipeline2ServiceRequest3: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "pipeline 2 service3",
        "service3.com",
        "rest",
        "delete",
        "pipeline 2 request body"
      )

      //////////////////////////////////////////////////////////////////////////
      // Pipeline 3 service and middleware requests
      //////////////////////////////////////////////////////////////////////////
      val mockPipeline3MiddlewareRequest1: MiddlewareRequest =
        ObjectMother.getMockMiddlewareRequest(
        "pipeline 3 middleware1",
        "middleware1.com",
        "rest",
        "post",
        "pipeline 3 request body",
        "user authorized"
      )

      val mockPipeline3MiddlewareRequest2: MiddlewareRequest =
        ObjectMother.getMockMiddlewareRequest(
        "pipeline 3 middleware2",
        "middleware2.com",
        "rest",
        "delete",
        "pipeline 3 request body",
        "user authorized"
      )

      val mockPipeline3MiddlewareRequest3: MiddlewareRequest =
        ObjectMother.getMockMiddlewareRequest(
        "pipeline 3 middleware3",
        "middleware3.com",
        "rest",
        "patch",
        "pipeline 3 request body",
        "user authorized"
      )

      val mockPipeline3MiddlewareRequest4: MiddlewareRequest =
        ObjectMother.getMockMiddlewareRequest(
        "pipeline 3 middleware4",
        "middleware4.com",
        "rest",
        "put",
        "pipeline 3 request body",
        "rate limit not exceeded"
      )

      val mockPipeline3ServiceRequest1: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "pipeline 3 service1",
        "service1.com",
        "rest",
        "patch",
        "pipeline 3 request body"
      )

      val mockPipeline3ServiceRequest2: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "pipeline 3 service2",
        "service2.com",
        "rest",
        "put",
        "pipeline 3 request body"
      )

      val mockPipeline3ServiceRequest3: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "pipeline 3 service3",
        "service3.com",
        "rest",
        "delete",
        "pipeline 3 request body"
      )

      val mockPipeline3ServiceRequest4: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "pipeline 3 service4",
        "service4.com",
        "rest",
        "delete",
        "pipeline 3 request body"
      )

      val mockPipeline3ServiceRequest5: ServiceRequest =
        ObjectMother.getMockServiceRequest(
        "pipeline 3 service5",
        "service5.com",
        "rest",
        "delete",
        "pipeline 3 request body"
      )

      //////////////////////////////////////////////////////////////////////////
      // Create 3 pipelines with their own service and middleware requests
      //////////////////////////////////////////////////////////////////////////
      val mockPipeline1: Pipeline = ObjectMother.getMockPipeline(
        "pipeline1 with many services",
        Vector(
          mockPipeline1MiddlewareRequest1,
          mockPipeline1MiddlewareRequest2,
          mockPipeline1ServiceRequest1,
          mockPipeline1ServiceRequest2,
          mockPipeline1ServiceRequest3
        )
      )

      val mockPipeline2: Pipeline = ObjectMother.getMockPipeline(
        "pipeline2 with many services",
        Vector(
          mockPipeline2MiddlewareRequest1,
          mockPipeline2MiddlewareRequest2,
          mockPipeline2MiddlewareRequest3,
          mockPipeline2MiddlewareRequest4,
          mockPipeline2ServiceRequest1,
          mockPipeline2ServiceRequest2,
          mockPipeline2ServiceRequest3
        )
      )

      val mockPipeline3: Pipeline = ObjectMother.getMockPipeline(
        "pipeline3 with many services",
        Vector(
          mockPipeline3MiddlewareRequest1,
          mockPipeline3MiddlewareRequest2,
          mockPipeline3MiddlewareRequest3,
          mockPipeline3MiddlewareRequest4,
          mockPipeline3ServiceRequest1,
          mockPipeline3ServiceRequest2,
          mockPipeline3ServiceRequest3,
          mockPipeline3ServiceRequest4,
          mockPipeline3ServiceRequest5
        )
      )

      // function under test
      val mockPipelines =
        pipelineFactory
        .createPipelines(MockPipelineJson.mockPipelineJson6)

      assert(ObjectMatcher.areObjectsEqual(
        mockPipeline1,
        mockPipelines("pipeline1 with many services"))
      )
      assert(ObjectMatcher.areObjectsEqual(
        mockPipeline2,
        mockPipelines("pipeline2 with many services"))
      )
      assert(ObjectMatcher.areObjectsEqual(
        mockPipeline3,
        mockPipelines("pipeline3 with many services"))
      )
      mockPipelines("pipeline1 with many services") shouldBe a [Pipeline]
      mockPipelines("pipeline2 with many services") shouldBe a [Pipeline]
      mockPipelines("pipeline3 with many services") shouldBe a [Pipeline]
      assert(mockPipelines.size == 3)
    }

  }

}
