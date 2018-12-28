package unitTest

import org.scalatest.FunSuite

class testing1Test extends FunSuite{
  test("Testing interest"){
    assert(testing1.interst(10000, 5.6, 5)===2800)
  }

}