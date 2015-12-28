import java.util.concurrent.Executors

import scala.concurrent.Await
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

object Futures extends App {
  
  val pool = Executors.newCachedThreadPool()
  implicit val ec = ExecutionContext.fromExecutorService(pool)
  
  val future = Future {
    1 + 1
  }
  
  val result = Await.result(future, 1.seconds)
  println(result)
  
  pool.shutdown

}