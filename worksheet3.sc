object worksheet3 {

	import scala.concurrent.Await
	import scala.concurrent.Future
	import akka.pattern.ask
	import akka.util.Timeout
	import scala.concurrent.duration._
	import scala.concurrent.ExecutionContext.Implicits.global
	
	//implicit val timeout = Timeout(5 seconds)
	//val future = actor ? msg // enable by the ask import
	//val result = Await.result(future, timeout.duration).asInstanceOf[String]
	
	val future = Future {
		"Hello " + "World"
	}                                         //> future  : scala.concurrent.Future[String] = scala.concurrent.impl.Promise$De
                                                  //| faultPromise@39c0f4a
	
  future foreach println
  
  val f1  = Future {
  	"hello" + "world"
  }                                               //> Hello World
                                                  //| f1  : scala.concurrent.Future[String] = scala.concurrent.impl.Promise$Defaul
                                                  //| tPromise@13deb50e
  val f2 = f1 map { x => x.length }               //> f2  : scala.concurrent.Future[Int] = scala.concurrent.impl.Promise$DefaultPr
                                                  //| omise@598067a5
  
  f2 foreach println
  	
  val f3 = Future.successful(3)                   //> 10
                                                  //| f3  : scala.concurrent.Future[Int] = scala.concurrent.impl.Promise$KeptPromi
                                                  //| se@5f341870
  
  f3 foreach println

	val f4 = for {
		a <- Future(10 / 2) // 10 / 2 = 5
		b <- Future(a + 1) // 5 + 1 = 6
		c <- Future(a - 1) // 5 - 1 = 4
		if c > 3 // Future.filter
	} yield b * c // 6 * 4 = 24               //> 3
                                                  //| f4  : scala.concurrent.Future[Int] = scala.concurrent.impl.Promise$DefaultPr
                                                  //| omise@4cf777e8
	
	f4 foreach println

	println                                   //> 
}