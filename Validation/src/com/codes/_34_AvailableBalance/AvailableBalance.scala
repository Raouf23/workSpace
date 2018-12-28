package com.codes._34_AvailableBalance

import com.codes.Errors.ErrorCodes

object AvailableBalance extends App {

  val value = BigInt("99999999999")
  val maxValue = BigInt("9999999999")

  def validate() {
    if (value < 0 || value > maxValue) {
      val x = ErrorCodes("3402")
      println(x)
      val y = ErrorCodes("3401")
      println(y)
      val y1 = ErrorCodes("341101")
      println(y1)
    }
  }
  validate() 
}