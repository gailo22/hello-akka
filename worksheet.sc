object worksheet {
  
	import scala.concurrent._
	import scala.concurrent.ExecutionContext.Implicits.global
	import scala.concurrent.duration._
	import akka.actor._
	
	val future = Future {
		1 + 1
	}                                         //> future  : scala.concurrent.Future[Int] = scala.concurrent.impl.Promise$Defau
                                                  //| ltPromise@6ef1c8f0
	
	Await.result(future, 1.seconds)           //> res0: Int = 2
	
	
}