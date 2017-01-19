package hello.fib

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ReceiveTimeout
import akka.event.Logging
import scala.concurrent.duration._

class MyActor extends Actor {
  
  val log = Logging(context.system, this)
  
  context.setReceiveTimeout(30 milliseconds)
  
  def receive = {
    case "test" => log.info("received test")
    case ReceiveTimeout => throw new RuntimeException("received timeout")
    case _      => log.info("received unknown message")
  }
  
}