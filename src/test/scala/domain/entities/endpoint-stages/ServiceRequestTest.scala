import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter

import requeststages.ServiceRequest

final class ServiceRequestTest extends FunSpec with BeforeAndAfter {

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

  var serviceRequest: ServiceRequest = _

  before {
    serviceRequest = new ServiceRequest()
  }

  describe("setName() / getName() tests") {
    it("should set and get the name (an empty string)") {
      serviceRequest.setName(EmptyMockString)
      assert(serviceRequest.getName() == EmptyMockString)
    }

    it("should set and get the name (a short string)") {
      serviceRequest.setName(ShortMockString)
      assert(serviceRequest.getName() == ShortMockString)
    }

    it("should set and get the name (a long string)") {
      serviceRequest.setName(LongMockString)
      assert(serviceRequest.getName() == LongMockString)
    }
  }

  describe("setUrl() / getUrl() tests") {
    it("should set and get the url (an empty string)") {
      serviceRequest.setUrl(EmptyMockString)
      assert(serviceRequest.getUrl() == EmptyMockString)
    }

    it("should set and get the url (a short string)") {
      serviceRequest.setUrl(ShortMockString)
      assert(serviceRequest.getUrl() == ShortMockString)
    }

    it("should set and get the url (a long string)") {
      serviceRequest.setUrl(LongMockString)
      assert(serviceRequest.getUrl() == LongMockString)
    }
  }

  describe("setProtocol() / getProtocol() tests") {
    it("should set and get the protocol (an empty string)") {
      serviceRequest.setProtocol(EmptyMockString)
      assert(serviceRequest.getProtocol() == EmptyMockString)
    }

    it("should set and get the protocol (a short string)") {
      serviceRequest.setProtocol(ShortMockString)
      assert(serviceRequest.getProtocol() == ShortMockString)
    }

    it("should set and get the protocol (a long string)") {
      serviceRequest.setProtocol(LongMockString)
      assert(serviceRequest.getProtocol() == LongMockString)
    }
  }

  describe("setMethod() / getMethod() tests") {
    it("should set and get the method (an empty string)") {
      serviceRequest.setMethod(EmptyMockString)
      assert(serviceRequest.getMethod() == EmptyMockString)
    }

    it("should set and get the method (a short string)") {
      serviceRequest.setMethod(ShortMockString)
      assert(serviceRequest.getMethod() == ShortMockString)
    }

    it("should set and get the method (a long string)") {
      serviceRequest.setMethod(LongMockString)
      assert(serviceRequest.getMethod() == LongMockString)
    }
  }

}
