package hello.fib

import akka.actor.{ Actor, Props, Terminated }

class WatchActor extends Actor {
  
  val child = context.actorOf(Props.empty, "child")
  context.watch(child)
  var lastSender = context.system.deadLetters
  
  def receive = {
    case "kill" => context.stop(child); lastSender = sender
    case Terminated(`child`) => lastSender ! "finished"
  }
  
}