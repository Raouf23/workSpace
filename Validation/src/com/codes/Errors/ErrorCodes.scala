package com.codes.Errors

import com.typesafe.config.ConfigFactory
import com.typesafe.config.ConfigException

class ErrorCodes(errCode: String = "0000", error: String = "Error code passed does not belong to any Error, INVALID ERROR CODE") {
  override def toString = "ERROR CODE : " + errCode + " \n -> " + error
}
object ErrorCodes {
  val svcf = ConfigFactory.load()
  def apply(errCode: String): ErrorCodes = {
    try {
      val error = svcf.getString("Error.code." + errCode)
      new ErrorCodes(errCode, error)
    } catch {
      // Log E
      case ex: ConfigException => new ErrorCodes(errCode)
      case _: Throwable        => new ErrorCodes
    }

  }
}