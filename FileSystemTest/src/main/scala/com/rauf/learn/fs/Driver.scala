package com.rauf.learn.fs

object Driver extends App {
 
    val s1 = new Stock
    s1.setPrice(20)
    
    val s2 = new Stock
    s2.setPrice(100)
    
    println(s2.isHigher(s1))
}