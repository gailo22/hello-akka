package hello.barista

import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.ActorRef

sealed trait CoffeeRequest
case object CappuccinoRequest extends CoffeeRequest
case object EspressoRequest extends CoffeeRequest

case class Bill(cents: Int)
case object ClosingTime


class Barista extends Actor {

  def receive = {
    case CappuccinoRequest => 
      //sender ! Bill(250)
      println("I have to prepare a cappuccino!")
    case EspressoRequest => 
      //sender ! Bill(200)
      println("Let's prepare an espresso!")
    case ClosingTime =>
      context.system.terminate()
  }
  
}

case object CaffeineWithdrawalWarning
class Customer(caffeineSource: ActorRef) extends Actor {
  def receive = {
    case CaffeineWithdrawalWarning => caffeineSource ! EspressoRequest
    case Bill(cents) => println(s"I have to pay $cents cents, or else!")
  }
}