package com.raouf.UserStory3

class AccValidator(checkingAccData:CheckingAccData,subscriberValidator:SubscriberValidator) {
  def validation(acc_no:Long){
    val checkingdetails = checkingAccData.getCheckingAccData(acc_no)
    subscriberValidator.validateSubscriber(checkingdetails.subscriber_Code)
  }
}