package com.rauf.learn.fs

class Stock
{
  private var Price:Double = _
  
  def setPrice(p:Double)= {
      Price =p
  }
  
  def isHigher(that:Stock):Boolean = this.Price>that.Price
}