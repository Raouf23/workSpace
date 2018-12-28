package com.codes._14_PaymentPeriodicity

import com.codes.Errors.ErrorCodes

object paymentPeriodicity extends App {

  val validPeriodicityRange = List(1, 2, 3, 4, 5, 6, 9)

  val periodicityValFrmFile = 8

  val accTypeVal = 45

  def periodicityValidator(Value: Int, accVal: Int, validRange: List[Int]): Boolean = (accVal) match {
    case 2 =>
      if (Value != 1) {
        val x = ErrorCodes("1402")
        println(x)
        false
      } else { true }

    case accVal if 3 to 50 contains accVal =>
      if (validRange contains Value)
        true
      else {
        val x = ErrorCodes("1401")
        println(x)
        false
      }
  }

  periodicityValidator(periodicityValFrmFile, accTypeVal, validPeriodicityRange)

}