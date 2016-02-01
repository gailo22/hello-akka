package hello.barista

import akka.actor.Actor
import akka.actor.ActorRef
import akka.event.Logging
import akka.actor.Props
import akka.actor.ActorSystem
import scala.io.Source
import scala.collection._

class HelloActor(val hello: String) extends Actor {
  val log = Logging(context.system, this)
  
  def receive = {
    case `hello` =>
      log.info(s"Received a '$hello'...$hello!")
    case msg =>
      log.info(s"Unexpected message '$msg'")
      context.stop(self)
  }
  
}

object HelloActor {
  def props(hello: String) = Props(new HelloActor(hello))
  def propsVariant(hello: String) = Props(classOf[HelloActor], hello)
}

object ActorsCreate extends App {
  lazy val system = ActorSystem("HelloActorSystem")
  val hiActor: ActorRef = system.actorOf(HelloActor.props("hi"), name = "greeter")
  hiActor ! "hi"
  Thread.sleep(1000)
  hiActor ! "hola"
  Thread.sleep(1000)
  system.shutdown()  
}