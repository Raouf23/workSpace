package com.codes._1_IDType

import com.codes.Errors.ErrorCodes

object IDType extends App {

  val IDTypeValue: Int = 5
  val ValidValuesRange = 1 to 4
  val AccountType = 0
  val IDNum = 9999000099999L
  def isValid(IDNum: Long): Boolean = false

  def Pre_validation(): Int = {
    if (!ValidValuesRange.contains(IDTypeValue) && isValid(IDNum)) {
      if ((IDNum > 0 && IDNum < 99999999L) || (IDNum > 1000000000L && IDNum < 7999999999L)) { 1 }
      else if ((IDNum > 800000000L && IDNum <= 999999999L) || (IDNum > 8000000000L && IDNum < 9999999999L)) { 2 }
      else if ((IDNum > 600000000L && IDNum < 799999999L) || (IDNum > 6000000000L && IDNum < 7999999999L)) { 3 }
      else { 4 }
    } else IDTypeValue
  }
  def validate1() {
    if (!ValidValuesRange.contains(Pre_validation())) {
      val x = ErrorCodes("0131")
      println(x)
    }
  }
  def validate2() {
    if ((IDTypeValue == 2 && Pre_validation() == 3) || (IDTypeValue == 3 && Pre_validation() == 2)) {
      val z = ErrorCodes("0102")
      println(z)
    }
  }
  def validate3() {
    if ((Pre_validation() != (1) || Pre_validation() != (4)) && AccountType == 21) {
      val y = ErrorCodes("0103")
      println(y)
    }
  }
  validate1()
  validate2()
  validate3()
}