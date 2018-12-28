package com.rauf.learn.fs

import org.scalatest.FunSuite
import org.joda.time.format.DateTimeFormat

class DataValidationTest  extends FunSuite{
 
  val fmt = DateTimeFormat.forPattern("yyyyMMdd")
  test("Date Validation test 1"){ 
    val test1 = fmt.parseDateTime("20060718")
    assert(DataValidation.dateValidation(test1))
  }
  
  test("Date Validation test 2"){ 
    val test2 = fmt.parseDateTime("20000229")
    assert(DataValidation.dateValidation(test2))
  }
  
  test("Date Validation test 3"){ 
    val test3 = fmt.parseDateTime("20000431")
    assert(DataValidation.dateValidation(test3))
  }
  
   test("Date Validation test 4"){ 
    val test4 = fmt.parseDateTime("20010229")
    assert(DataValidation.dateValidation(test4))
  }
   
   test("Date Validation test 5"){ 
    val test5 = fmt.parseDateTime("20070132")
    assert(DataValidation.dateValidation(test5))
  }
   
   test("Date Validation test 6"){ 
    val test6 = fmt.parseDateTime("20091311")
    assert(DataValidation.dateValidation(test6))
  }
   
   test("Date Validation test 7"){ 
    val test6 = fmt.parseDateTime("00000000")
    assert(DataValidation.dateValidation(test6))
  }
   
   test("Numeric test 1"){
     assert(DataValidation.isNumeric("Angela"))
   }
   
   test("Numeric test 2"){
     assert(DataValidation.isNumeric("123"))
   }
   
   test("Numeric test 3"){
     assert(DataValidation.isNumeric("23.45"))
   }
   
   test("Numeric test 4"){
     assert(DataValidation.isNumeric("23EF4D"))
   }
   
   test("Numeric test 5"){
     assert(DataValidation.isNumeric(""))
   }
   
    test("Numeric test 6"){
     assert(DataValidation.isNumeric("00000000"))
   }
   test("idValidation Test 1"){
     assert(DataValidation.idValidation(3, 799999998))
   }
}