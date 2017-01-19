package hello.fib

import akka.actor._

class HotSwapActor extends Actor {
  
  import context._
  
  def angry: Receive = {
    case "foo" => sender ! "I am already angry!!"
    case "bar" => become(happy)
  }
  
  def happy: Receive = {
    case "foo" => sender ! "I am already happy -)"
    case "bar" => become(angry)
  }
  
  def receive = {
    case "foo" => become(angry)
    case "bar" => become(happy)
  }
  
}