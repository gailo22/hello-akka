package hello.fib

import akka.actor._
import akka.event._

case object Swap

class Swapper extends Actor {
  
  import context._
  val log = Logging(context.system, this)
  
  def receive = {
    case Swap => 
      log.info("Hi")
      become {
        case Swap => 
          log.info("Ho")
          unbecome()
      }
  }
}

object SwapperApp extends App {
  val system = ActorSystem("SwapperSystem")
  val swap = system.actorOf(Props[Swapper], "swapper")
  
  swap ! Swap
  swap ! Swap
  swap ! Swap
  swap ! Swap
  swap ! Swap
  swap ! Swap
}