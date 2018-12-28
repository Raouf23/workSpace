package com.raouf.UserStory3

import org.scalatest.FlatSpec
import org.scalamock.scalatest.MockFactory

class AccValidatorTest extends FlatSpec with MockFactory {
  val checkingData1 = checkingData(subscriber_Code = 510055, AccNO = 111769576193L)
  val checkingData2 = checkingData(subscriber_Code = 100556, AccNO = 111769576193L)
  
  val SubscriberValidatorMock = mock[SubscriberValidator]
  val checkingDataStub = stub[CheckingAccData]

  "AccValidator" should "validate the subscriber code" in {

    //set expectations
    (SubscriberValidatorMock.validateSubscriber _).expects(510055)

    //configure stub
    (checkingDataStub.getCheckingAccData _).when(111769576193L).returns(checkingData1)

    //run system under test
    val validatorResultObserver = new AccValidator(checkingDataStub, SubscriberValidatorMock)
    validatorResultObserver.validation(111769576193L)
    
  }
}