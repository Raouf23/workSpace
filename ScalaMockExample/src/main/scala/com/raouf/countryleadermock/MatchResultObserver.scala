package com.raouf.countryleadermock

class MatchResultObserver(playerData:PlayerData, countryBoard:CountryBorad) {
  
  def recordMatchResult(result:MatchResult):Unit = {
    val player = playerData.getPlayerById(result.winner)
    
    countryBoard.addVictoryForCountry(player.country)
  } 
  
}