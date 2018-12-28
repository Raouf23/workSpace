package com.raouf.countryleadermock

import org.scalatest.FlatSpec
import org.scalamock.scalatest.MockFactory

class MatchResultObserverTest extends FlatSpec with MockFactory{
  val winner = Player(id =22,name ="Roman",country ="Itlay")
  val loser = Player(id =33,name ="Bruan",country ="USA")
  
  "MatchResultObserver" should "update CountryBoard after finished match" in{
    
    val countryBoradMock = mock[CountryBorad]
    val playerDetailStub = stub[PlayerData]
    
    //set expectations
    (countryBoradMock.addVictoryForCountry _).expects("Itlay")
    
    
    
    //configure stub
    (playerDetailStub.getPlayerById _).when(loser.id).returns(loser)
    (playerDetailStub.getPlayerById _).when(winner.id).returns(winner)
    
    //run system under test
    val matchResultObserver = new MatchResultObserver(playerDetailStub, countryBoradMock)
    
    matchResultObserver.recordMatchResult(MatchResult(winner = winner.id,loser=loser.id))
    
    
  }
}