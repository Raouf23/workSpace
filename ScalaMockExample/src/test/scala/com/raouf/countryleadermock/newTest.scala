package com.raouf.countryleadermock

import org.scalatest.FlatSpec
import org.scalamock.scalatest.MockFactory

class newTest extends FlatSpec with MockFactory{
  
  val m = mockFunction[Int, String]
  
  "Function" should "return String representation of number" in {
    //set expectations 
    m expects (42) returning "Forty three" once
    
    //run
    val str = m(42)
    assert(str ==="Forty three")
  }
}