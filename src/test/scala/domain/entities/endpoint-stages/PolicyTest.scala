import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter

import endpointstages.Policy

final class PolicyTest extends FunSpec with BeforeAndAfter {

  final val EmptyMockString = ""
  final val ShortMockString = "rest"
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

  var policy: Policy = _

  before {
    policy = new Policy()
  }

  describe("setSuccessCondition() / getSuccessCondition() tests") {
    it("should set and get the success condition (an empty string)") {
      policy.setSuccessCondition(EmptyMockString)
      assert(policy.getSuccessCondition() == EmptyMockString)
    }

    it("should set and get the success condition (a short string)") {
      policy.setSuccessCondition(ShortMockString)
      assert(policy.getSuccessCondition() == ShortMockString)
    }

    it("should set and get the success condition (a long string)") {
      policy.setSuccessCondition(LongMockString)
      assert(policy.getSuccessCondition() == LongMockString)
    }
  }

}
