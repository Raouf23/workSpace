package com.codes._8_TypeOfDebtor

import com.codes.Errors.ErrorCodes

object TypeOfDebtor extends App {

  val debtorCode = List("00", "01", "04", "05", "06", "07", "08", "96", "97")

  val debtorCodeFrmFile = "09"

  def debtorTypeValidation(code: String, Dcode: List[String]): Boolean = {
    if (Dcode.contains(code))
      return true
    else {
      val x = ErrorCodes("0801")
      println(x)
      return false
    }
  }

  println(debtorTypeValidation(debtorCodeFrmFile, debtorCode))

  def matchTest(accType: Int, creditType: Int) = (accType, creditType) match {
    case (x, y) if (x == 13 && y != 3) => 
      {
        val x = ErrorCodes("09011")
        println(x)
     }
    case (x, y) if !((1 to 51 contains x) && (1 to 6 contains y)) => 
      {
        val x = ErrorCodes("09012")
        println(x)
      }
    case (x,y) if (x ==2 && (y !=1 && y != 2 && y !=5)) => 
      {
        val x =ErrorCodes("09013")
        println(x)
      }
    case (x,y) if (y==3 && (x !=1 && x !=13 && x != 51)) => 
      {
         val x =ErrorCodes("0902")
         println(x)
      }
    case _ => println("default")
  }

  matchTest(50, 3)

}