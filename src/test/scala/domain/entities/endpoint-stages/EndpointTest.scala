import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter

import endpointstages.Endpoint

final class EndpointTest extends FunSpec with BeforeAndAfter {

  final val EmptyMockString = ""
  final val ShortMockString = "Short name"
  final val LongMockString = """Lorem ipsum dolor sit amet, consectetuer adipiscing
  elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque
  penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam
  felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa
  quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu.
  In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum
  felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum
  semper nisi. Aenean vulputate eleifednd tellus. Aenean leo ligula, porttitor eu,
  semper nisi. Aenean vulputate eleifedGP*(Ynd tellus. Aenean leo ligula, porttitor eu,
  consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis,
  feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum.
  Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies
  nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum
  rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam
  quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio
  et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam
  quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla
  mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget
  bibendum sodales, augue velit cur123sus nunc,"""

  var endpoint: Endpoint = _

  before {
    endpoint = new Endpoint()
  }

  describe("setName() / getName() tests") {
    it("should set and get the name (an empty string)") {
      endpoint.setName(EmptyMockString)
      assert(endpoint.getName() == EmptyMockString)
    }

    it("should set and get the name (a short string)") {
      endpoint.setName(ShortMockString)
      assert(endpoint.getName() == ShortMockString)
    }

    it("should set and get the name (a long string)") {
      endpoint.setName(LongMockString)
      assert(endpoint.getName() == LongMockString)
    }
  }

  describe("setUrl() / getUrl() tests") {
    it("should set and get the url (an empty string)") {
      endpoint.setUrl(EmptyMockString)
      assert(endpoint.getUrl() == EmptyMockString)
    }

    it("should set and get the url (a short string)") {
      endpoint.setUrl(ShortMockString)
      assert(endpoint.getUrl() == ShortMockString)
    }

    it("should set and get the url (a long string)") {
      endpoint.setUrl(LongMockString)
      assert(endpoint.getUrl() == LongMockString)
    }
  }

  describe("setProtocol() / getProtocol() tests") {
    it("should set and get the protocol (an empty string)") {
      endpoint.setProtocol(EmptyMockString)
      assert(endpoint.getProtocol() == EmptyMockString)
    }

    it("should set and get the protocol (a short string)") {
      endpoint.setProtocol(ShortMockString)
      assert(endpoint.getProtocol() == ShortMockString)
    }

    it("should set and get the protocol (a long string)") {
      endpoint.setProtocol(LongMockString)
      assert(endpoint.getProtocol() == LongMockString)
    }
  }

  describe("setMethod() / getMethod() tests") {
    it("should set and get the method (an empty string)") {
      endpoint.setMethod(EmptyMockString)
      assert(endpoint.getMethod() == EmptyMockString)
    }

    it("should set and get the method (a short string)") {
      endpoint.setMethod(ShortMockString)
      assert(endpoint.getMethod() == ShortMockString)
    }

    it("should set and get the method (a long string)") {
      endpoint.setMethod(LongMockString)
      assert(endpoint.getMethod() == LongMockString)
    }
  }

}
