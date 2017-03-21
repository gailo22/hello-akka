package hello.fib

import scala.concurrent.duration._
import akka.pattern.CircuitBreaker
import akka.pattern.pipe
import akka.actor.{ Actor, ActorLogging, ActorRef }

import scala.concurrent.Future

class DangerousActor extends Actor with ActorLogging {
  
  import context.dispatcher
  
  val breaker =
    new CircuitBreaker(
        context.system.scheduler,
        maxFailures = 5,
        callTimeout = 10.seconds,
        resetTimeout = 1.minute).onOpen(notifyMeOnOpen())

  def notifyMeOnOpen(): Unit = 
    log.warning("My CircuitBreaker is now open, and will not close for one minute")
    
  def dangerousCall: String = "This really isn't that dangerous of a call after all"

  def receive = {
    case "is my middle name" => 
      breaker.withCircuitBreaker(Future(dangerousCall)) pipeTo sender()
      
    case "block for me" => 
      sender() ! breaker.withSyncCircuitBreaker(dangerousCall)
  }
  
}