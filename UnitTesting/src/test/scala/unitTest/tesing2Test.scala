package unitTest

import org.scalatest.FlatSpec

class tesing2Test extends FlatSpec {
  "A empty set" should "have size 0" in {
    assert(Set.empty.size ==0)
  }
  "A method" should "have result 2600" in {
    assert(testing1.interst(10000, 5.6, 5)==2800)
  }
}