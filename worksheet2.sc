object worksheet2 {
  
  import hello.barista._
  import akka.actor._
  
  val system = ActorSystem("Barista")             //> system  : akka.actor.ActorSystem = akka://Barista
  val barista = system.actorOf(Props[Barista], "Barista")
                                                  //> barista  : akka.actor.ActorRef = Actor[akka://Barista/user/Barista#-12285440
                                                  //| 55]
  val customer = system.actorOf(Props(classOf[Customer], barista), "Customer")
                                                  //> customer  : akka.actor.ActorRef = Actor[akka://Barista/user/Customer#-434024
                                                  //| 470]
  
  
  barista ! CappuccinoRequest
  barista ! EspressoRequest                       //> I have to prepare a cappuccino!
  println("I ordered a cappuccino and an espresso")
                                                  //> I ordered a cappuccino and an espresso
  
  //customer ! CaffeineWithdrawalWarning
  barista ! ClosingTime                           //> Let's prepare an espresso!
  
  
  //import akka.pattern.ask
  //import akka.util.Timeout
  //import scala.concurrent.duration._
  
  //implicit val timeout = Timeout(2.second)
  //implicit val ec = system.dispather
  
  //val f: Future[Any] = barista2 ? CappuccinoRequest
  //f.onsuccess {
  //  case Bill(cents) => println(s"Will pay $cents cents for a cappuccino")
  //}
  
  
}