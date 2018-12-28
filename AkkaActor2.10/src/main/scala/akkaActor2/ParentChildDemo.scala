package akkaActor2

import akka.actor._
import jdk.internal.org.objectweb.asm.Context

case class Name(name:String)
case class CreateChild(name:String)

class Child extends Actor{
  
  var name="No name"
  
  override def postStop
  {
    println(s"D'oh! They killed me ($name): ${self.path}")
  }
  def receive = {
      case Name(name) => this.name = name
      case _ => println(s"Child $name got message")
}
}

class Parent extends Actor
{
    def receive ={
      case CreateChild(name)=>
        println(s"Parent about to create Child ($name) ...") //Parent creates a new Child here
        val child = context.actorOf(Props[Child], name = s"$name")
        child ! Name(name)
      case _ => print("Parent got some other messages")
      
    }  
}

object ParentChildDemo extends App{
  
  val actorSystem = ActorSystem("ParentChildTest")
  val parent = actorSystem.actorOf(Props[Parent], name = "Parent")
  
  // send messages to Parent to create to child actors
  parent ! CreateChild("Jonathan")
  parent ! CreateChild("Jordan")
  Thread.sleep(500)
  
  // lookup Jonathan, then kill it
    println("Sending Jonathan a PoisonPill ...")
  val jonathan = actorSystem.actorSelection("/user/Parent/Jonathan")
  //jonathan ! PoisonPill
  println("jonathan was killed")
  Thread.sleep(5000)
  
  actorSystem.stop(parent)

  
  
  
}