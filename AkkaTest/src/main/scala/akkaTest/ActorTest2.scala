package akkaTest

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

class ActorTest2(myName:String) extends Actor
{
  def receive = {
// (2) println statements changed to show the name
    case "hello" => println(s"hello from $myName")
    case _ => println(s"'huh?', said $myName")
  } 
 
}


object main extends App{
  
    val system1 =  ActorSystem("HelloSystem")
  
  // create and start the actor
   val ActorTest = system1.actorOf(Props(new ActorTest2("Fred")), name = "ActorTest")
// send the actor two messages
ActorTest ! "hello"
ActorTest ! "buenos dias"

system1.stop(ActorTest)
// shut down the system
//system1.shutdown()
}