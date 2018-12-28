package com.rauf.learn.fs

object StringComparison {
  def main(args: Array[String]) {

    val str1 = "12345"
    val str2 = "12346"
    val str3 = "1234"
    val str4 = "12376"
    val str5 = ""
    val str6 = "32345"
    val str7 = "123945"
    val str8 = "12385"
    val str9 = "1234589"
    val str10 = "123"
    val str11 = "1235"
    val str12 = "12345"

    val name1 = "PADRO AADRO PASCEL GONELEZ"
    val name2 = ""

    idNamecompairson(name1, name2)

    def idNamecompairson(s1: String, s2: String) = {
      val thisSegment = s1.split(" ").sorted.toList
      val thatSegment = s2.split(" ").sorted.toList
    
      val l1 = (thisSegment diff thatSegment).size
      val l2 = (thatSegment diff thisSegment).size
    println(l1)
      println(l2)

    }

    /*    println("Test-2")
    println(comparisonMethod1(str1, str2))
    println("Test-3")
    println(comparisonMethod1(str1, str3))
    println("Test-4")
    println(comparisonMethod1(str1, str4))
    println("Test-5")
    println(comparisonMethod1(str1, str5))
    println("Test-6")
    println(comparisonMethod1(str1, str6))
    println("Test-7")
    println(comparisonMethod1(str1, str7))
    println("Test-8")
    println(comparisonMethod1(str1, str8))
    println("Test-9")
    println(comparisonMethod1(str1, str9))*/

    /*   println("Test-2 -partial")
    println(comparisonMethod2(str1, str2))
    println("Test-3- partial")
    println(comparisonMethod2(str1, str3))
    println("Test-4 - Not matched ")
    println(comparisonMethod2(str1, str4))
    println("Test-5 - Not Matched")
    println(comparisonMethod2(str1, str5))
    println("Test-6 - Partial")
    println(comparisonMethod2(str1, str6))
    println("Test-7 - Not matched")
    println(comparisonMethod2(str1, str7))
    println("Test-8 - partial")
    println(comparisonMethod2(str1, str8))
    println("Test-9 - not matched")
    println(comparisonMethod2(str1, str9))
    println("Test-10")
    println(comparisonMethod2(str1, str10))
    println("Test-11")
    println(comparisonMethod2(str1, str11))
    println("Test-12")
    println(comparisonMethod2(str1, str12))
    */

    /*println("Test-2 -partial")
    println(comparisonMethod3(str1, str2))
    println("Test-3- partial")
    println(comparisonMethod3(str1, str3))
    println("Test-4 - Not matched ")
    println(comparisonMethod3(str1, str4))
    println("Test-5 - Not Matched")
    println(comparisonMethod3(str1, str5))
    println("Test-6 - Partial")
    println(comparisonMethod3(str1, str6))
    println("Test-7 - Not matched")
    println(comparisonMethod3(str1, str7))
    println("Test-8 - partial")
    println(comparisonMethod3(str1, str8))
    println("Test-9 - not matched")
    println(comparisonMethod3(str1, str9))
    println("Test-10")
    println(comparisonMethod3(str1, str10))
    println("Test-11")
    println(comparisonMethod3(str1, str11))*/
  }

  def comparisonMethod1(s1: String, s2: String): String = {

    if (Math.abs(s1.length() - s2.length()) > 1) {
      "Not matched"
    } else {
      if ((s1.diff(s2)).length() > 1) {
        "Not matched"
      } else
        "Partial match"
    }
  }

  def comparisonMethod2(s1: String, s2: String): Int = {
    if (Math.abs(s1.length() - s2.length()) > 1) {
      2
    } else if (Math.abs(s1.length() - s2.length()) == 1) {
      if (s1.zip(s2).count { case (charA, charB) => charA != charB } > 0) 2
      else 1

    } else
      s1.zip(s2).count { case (charA, charB) => charA != charB }
  }

  /*  def comparisonMethod3(s1:String,s2:String):String = {
    var matchingResult = 0 
    if(Math.abs(s1.length() - s2.length()) < 1)
    {
      for(i <- 0 to s1.length();j <- 0 to s2.length())
      {
        val currentMtacheResult  = s1.charAt(i) == s2.charAt(j)
        if(currentMtacheResult == false)
        {matchingResult = matchingResult +1}
      }
      if(matchingResult < 1 ) "Partial match"
      else "Not matched"
    }
    else{
      "Length of two string has more diference"
      }
  }*/

}