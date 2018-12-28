package com.raouf.countryleadermock

trait Turtle {
  def setPosition(x:Double,y:Double)
  def forward(x:Int)
  def getPosition(x:Double,y:Double)
  def takesRepeatedParameter(x: Int, ys: String*)
}