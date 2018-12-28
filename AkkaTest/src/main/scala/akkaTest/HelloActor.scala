package akkaTest

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

class HelloActor extends Actor {
  def receive ={
    case "hello" => print("Hello back to you")
    case _ =>   print("Huh")
  }
  
}

//object main extends App{
//  
//  val system =  ActorSystem("HelloSystem")
//  
//  // create and start the actor
//val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
//// send the actor two messages
//helloActor ! "hello"
//helloActor ! "buenos dias"
//// shut down the system
//  //system.shutdown()
//}