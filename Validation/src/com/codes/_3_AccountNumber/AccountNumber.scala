package com.codes._3_AccountNumber

import com.codes.Errors.ErrorCodes

object AccountNumber extends App {

  val value = "000000111769463176"
  val maxValue = BigInt("9999999999")

  def validate() {
    if (value.replaceAll("[+\\s|0]", "").isEmpty()) {
      val x = ErrorCodes("0301")
      println(x)
    }
    val y = ErrorCodes("3401")
    println(y)
    val y1 = ErrorCodes("341101")
    println(y1)

  }
  validate()
}