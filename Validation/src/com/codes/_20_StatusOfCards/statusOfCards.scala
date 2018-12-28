package com.codes._20_StatusOfCards

import com.codes.Errors.ErrorCodes

object statusOfCards extends App {

  val validStatusCodeForCards = List("1", "2", "3", "4", "5", "6", "7", "8")

  val cardStatusFromFile = "3"

  val accountType = 3

  def isNumeric(str: String) = scala.util.Try(str.toDouble).isSuccess

  def CardStatusValidation(status: String, accType: Int, statusRange: List[String]): Boolean = {
    if (isNumeric(status)) {
      if (accType == 2 && statusRange.contains(status)) {
        true
      } else if (accType != 2 && status.toInt > 0) {
        val x = ErrorCodes("2002")
        println(x)
        false
      } else {
        val x = ErrorCodes("2002")
        println(x)
        false
      }
    } else {
      val x = ErrorCodes("2001")
      println(x)
      false
    }
  }

  CardStatusValidation(cardStatusFromFile, accountType, validStatusCodeForCards)
}