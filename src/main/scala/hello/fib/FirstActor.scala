package hello.fib

import akka.actor.Actor
import akka.actor.Props

class FirstActor extends Actor {
  
  import context._
  val myActor = actorOf(Props[FirstActor], name="myactor")
  
  def receive = {
    case x => myActor ! x
  }
  
}