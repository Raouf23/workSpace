package com.rauf.learn.fs

class IdTypeEnum(idType: String, idNumber: String) {
/*
  object idTypeRange extends Enumeration {
    val minIdTypeRange3 = 600000000L
    val maxIdTypeRange3 = 799999999L
    val minIdTypeRange4 = 1L
    val maxIdTypeRange4 = 99999L
  }*/

  val IdTYPE1_RANGE = (600000000L, 799999999L)
  val IdTYPE2_RANGE = (1L, 99999L)

  //val IdRange: Array[Long] = Array(idTypeRange.minIdTypeRange3, idTypeRange.maxIdTypeRange3, idTypeRange.minIdTypeRange4, idTypeRange.maxIdTypeRange4)

  def splitIdType(RANGE: (Long, Long), idNumber: Long): String = {

    if (RANGE._1 until RANGE._2 contains idNumber)
      "3"

    else "4"
  }
  
  
  
}