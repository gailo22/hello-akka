package hello.pubsub

import akka.actor.Actor
import akka.actor.Props

class PubSubMediator extends Actor {
  override def receive = Actor.emptyBehavior
}

object PubSubMediator {
  final val Name = "pub-sub-mediator"
  
  def props: Props = Props(new PubSubMediator)
}
