package com.rauf.learn.fs



object CSVConverter {
  def main(args: Array[String]) {
    val sFile = """E:\logs\cslookup.txt"""
    val sData = scala.io.Source.fromFile(sFile)
    println("bureau_Event, Incoming_Event, D_0, D_1, D_2, D_3, D_4, D_5, D_6")
    for (line <- sData.getLines()) {
        println(line.replaceAll("(.{2})(?!$)", "$1,"))
    }
  }
}