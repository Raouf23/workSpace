package com.rauf.learn.fs

object Main extends App{
  object Weekday extends Enumeration{
    type WeekDay = Value
    
    val Mon,Tue,Wed,Thu,Fri,Sat,Sun = Value
    val accDate = Value("1").toString
    val Rating = Value("2").toString
    val valuemonth = Value("3").toString
  }
  
  import Weekday._
  
  def isWorkingDay(d: WeekDay) = ! (d == Sat || d == Sun)

  print(Weekday.ValueSet.toString()) 
  
  
 
  
}