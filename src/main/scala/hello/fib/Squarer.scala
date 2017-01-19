package hello.fib

import scala.concurrent.{ Promise, Future, Await }
import scala.concurrent.duration._
import akka.actor._

trait Squarer {

  def squareDontCare(i: Int): Unit //fire-forget
  
  def square(i: Int): Future[Int] //non-blocking send-request-reply
  
  def squareNowPlease(i: Int): Option[Int] //blocking send-request-reply
  
  def squareNow(i: Int): Int //blocking send-request-reply
  
  @throws(classOf[Exception]) //declare it or you will get an UndeclaredThrowableException
  def squareTry(i: Int): Int //blocking send-request-reply with possible exception

}

class SquarerImpl(val name: String) extends Squarer {
  
  def this() = this("default")
  
  def squareDontCare(i: Int): Unit = i * i
  
  def square(i: Int): Future[Int] = Future.successful(i * i)
  
  def squareNowPlease(i: Int): Option[Int] = Some(i * i)
  
  def squareNow(i: Int): Int = i * i
  
  def squareTry(i: Int): Int = throw new Exception("Catch me!")
  
}

object SquarerApp extends App {
  val system = ActorSystem("SquarerSystem")
  val mySquarer: Squarer = TypedActor(system).typedActorOf(TypedProps[SquarerImpl]())
  val otherSquarer: Squarer = TypedActor(system).typedActorOf(TypedProps(classOf[Squarer], new SquarerImpl("foo")), "name")

  println(mySquarer.squareNow(10))
  println(otherSquarer.squareNow(10))
}