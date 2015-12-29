object pingpong {
  import scala.concurrent._
	import scala.concurrent.ExecutionContext.Implicits.global
	import scala.concurrent.duration._
	import akka.actor._
	
	case object PingMessage
	case object PongMessage
	case object StartMessage
	case object StopMessage

  class Ping(pong: ActorRef) extends Actor {
    var count = 0
    def incrementAndPrint { count += 1; println("ping") }
    def receive = {
      case StartMessage =>
        incrementAndPrint
        pong ! PingMessage
      case PongMessage =>
        incrementAndPrint
        if (count > 99) {
          sender ! StopMessage
          println("ping stopped")
          context.stop(self)
        } else {
          sender ! PingMessage
        }
    }
  }

  class Pong extends Actor {
    def receive = {
      case PingMessage =>
        println(" pong")
        sender ! PongMessage
      case StopMessage =>
        println("pong stopped")
        context.stop(self)
    }
  }
	
	
	val system = ActorSystem("PingPongSystem")//> system  : akka.actor.ActorSystem = akka://PingPongSystem
	val pong = system.actorOf(Props[Pong], name = "pong")
                                                  //> pong  : akka.actor.ActorRef = Actor[akka://PingPongSystem/user/pong#1402046
                                                  //| 632]
	val ping = system.actorOf(Props(new Ping(pong)), name = "ping")
                                                  //> ping  : akka.actor.ActorRef = Actor[akka://PingPongSystem/user/ping#-120668
                                                  //| 9957]
	
	ping ! StartMessage
	
	system.shutdown                           //> ping
}