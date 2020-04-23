import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter

import servicestages.Service

final class ServiceTest extends FunSpec with BeforeAndAfter {

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

  var service: Service = _

  before {
    service = new Service()
  }

  describe("setName() / getName() tests") {
    it("should set and get the name (an empty string)") {
      service.setName(EmptyMockString)
      assert(service.getName() == EmptyMockString)
    }

    it("should set and get the name (a short string)") {
      service.setName(ShortMockString)
      assert(service.getName() == ShortMockString)
    }

    it("should set and get the name (a long string)") {
      service.setName(LongMockString)
      assert(service.getName() == LongMockString)
    }
  }

  describe("setUrl() / getUrl() tests") {
    it("should set and get the url (an empty string)") {
      service.setUrl(EmptyMockString)
      assert(service.getUrl() == EmptyMockString)
    }

    it("should set and get the url (a short string)") {
      service.setUrl(ShortMockString)
      assert(service.getUrl() == ShortMockString)
    }

    it("should set and get the url (a long string)") {
      service.setUrl(LongMockString)
      assert(service.getUrl() == LongMockString)
    }
  }

  describe("setProtocol() / getProtocol() tests") {
    it("should set and get the protocol (an empty string)") {
      service.setProtocol(EmptyMockString)
      assert(service.getProtocol() == EmptyMockString)
    }

    it("should set and get the protocol (a short string)") {
      service.setProtocol(ShortMockString)
      assert(service.getProtocol() == ShortMockString)
    }

    it("should set and get the protocol (a long string)") {
      service.setProtocol(LongMockString)
      assert(service.getProtocol() == LongMockString)
    }
  }

  describe("setMethod() / getMethod() tests") {
    it("should set and get the method (an empty string)") {
      service.setMethod(EmptyMockString)
      assert(service.getMethod() == EmptyMockString)
    }

    it("should set and get the method (a short string)") {
      service.setMethod(ShortMockString)
      assert(service.getMethod() == ShortMockString)
    }

    it("should set and get the method (a long string)") {
      service.setMethod(LongMockString)
      assert(service.getMethod() == LongMockString)
    }
  }

}
