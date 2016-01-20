package hello.pubsub

import akka.actor.Actor
import akka.actor.Props
import scala.concurrent.Await
import org.scalatest.BeforeAndAfterAll
import akka.actor.ActorSystem
import org.scalatest.Matchers
import scala.concurrent.duration.Duration
import org.scalatest.WordSpec
import akka.testkit.EventFilter

class PubSubMediator extends Actor {
  override def receive = Actor.emptyBehavior
}

object PubSubMediator {
  final val Name = "pub-sub-mediator"
  
  def props: Props = Props(new PubSubMediator)
}
