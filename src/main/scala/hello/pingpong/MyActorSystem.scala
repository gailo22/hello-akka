package hello.pingpong

import akka.actor.ActorSystem
import akka.actor.Props

object MyActorSystem {
  
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("PingPong")
    val pingPongActor = system.actorOf(Props[PingPongActor])
    
    pingPongActor ! PING
    Thread.sleep(1000)
    system.shutdown
  }

}