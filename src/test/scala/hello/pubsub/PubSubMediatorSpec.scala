package hello.pubsub

import scala.concurrent.Await
import org.scalatest.BeforeAndAfterAll
import akka.actor.ActorSystem
import org.scalatest.Matchers
import scala.concurrent.duration.Duration
import akka.testkit.EventFilter
import org.scalatest.WordSpec

class PubSubMediatorSpec extends WordSpec with Matchers with BeforeAndAfterAll {
 
  implicit val system = ActorSystem("pub-sub-mediator-spec-system")
 
  "A PubSubMediator" should {
    "be suited for getting started" in {
      EventFilter.debug(occurrences = 1, pattern = s"started.*${classOf[PubSubMediator].getName}").intercept {
        system.actorOf(PubSubMediator.props)
      }
    }
  }
 
  override protected def afterAll() = {
//    Await.ready(system.terminate(), Duration.Inf)
    system.terminate()
    //system.awaitTermination(Duration.Inf)
    
    super.afterAll()
  }
}