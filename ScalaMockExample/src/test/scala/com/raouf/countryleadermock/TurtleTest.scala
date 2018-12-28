
//package com.raouf.mock
//
//import org.scalatest.FlatSpec
//import org.scalamock.scalatest.MockFactory
//import org.scalamock.ProxyMockFactory
//
//class TurtleTest extends FlatSpec with MockFactory{
//  
//  val m = mock[Turtle]
//  
//  "proxyModes" should "set the position" in {
//    
//    m expects setPosition withArgs(10.0, 10.0)
//    m expects forward withArgs (5.0)
//    m expects getPosition returning (15.0, 10.0)
//    
//    m expects 'takesRepeatedParameter withArgs(42, **("red", "green", "blue"))
//  }
//}