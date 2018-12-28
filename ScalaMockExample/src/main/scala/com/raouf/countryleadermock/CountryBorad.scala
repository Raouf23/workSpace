package com.raouf.countryleadermock

trait CountryBorad {
  def addVictoryForCountry(country:String):Unit
  
  def getTopCountire():List[CountryBoradEntry]
}