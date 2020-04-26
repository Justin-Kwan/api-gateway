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

final class PipelineFactoryTest extends FunSpec with BeforeAndAfterEach with PrivateMethodTester with Matchers {

  private val pipelineFactory: PipelineFactory = new PipelineFactory()

  describe("createPipelines() test") {
    it("should create a map of 0 pipelines") {
      val pipelines = pipelineFactory.createPipelines(MockPipelineJson.mockPipelineJson1)
      assert(pipelines.isEmpty)
    }

    it("should create a map of 1 pipeline (holding 1 middleware and 1 ServiceRequest)") {
      val mockMiddlewareRequest1: MiddlewareRequest = ObjectMother.getMockMiddlewareRequest(
        "middleware1",
        "middleware1.com",
        "rest",
        "post",
        "request body",
        "user authorized"
      )
      val mockServiceRequest1: ServiceRequest = ObjectMother.getMockServiceRequest(
        "service1",
        "service1.com",
        "rest",
        "get",
        "request body"
      )
      val mockServiceRequests = Vector(mockMiddlewareRequest1, mockServiceRequest1)
      val mockPipeline: Pipeline = ObjectMother.getMockPipeline("pipeline1", mockServiceRequests)

      val mockPipelines = pipelineFactory.createPipelines(MockPipelineJson.mockPipelineJson2)
      val resPipeline: Pipeline = mockPipelines("pipeline1")

      assert(ObjectMatcher.areObjectsEqual(mockPipeline, resPipeline))

      mockPipelines("pipeline1") shouldBe a [Pipeline]
      assert(mockPipelines.size == 1)

      // assert mockPipeline == pipelines("pipeline1")
      // assert length of map is 1

    }

  }

}
