package com.codes._5_stateOfAccHldr

import com.codes.Errors.ErrorCodes

object stateOfAccHldr extends App{
  val validStateOfAccountHolder = 1 to 7
  val stateOfAccHolderFormFile  = "2"
  
  def stateValidation(stateOfAccHolder:String):String={
    if(validStateOfAccountHolder.contains(stateOfAccHolder.toInt)){
     postValidtion(stateOfAccHolder)
    }
    else{
      val x = ErrorCodes("0501")
      println(x)
      postValidtion(stateOfAccHolder)
    }
  }
  
  def postValidtion(stateOfAccHolder:String):String ={
    if(validStateOfAccountHolder.contains(stateOfAccHolder.toInt)){
     stateOfAccHolder
    }
    else{
      return ""
    }
  }
  
  stateValidation(stateOfAccHolderFormFile)

}