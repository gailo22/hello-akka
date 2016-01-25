package hello.scala

import akka.actor.Actor
import scala.concurrent.Future
import scala.util.{ Try, Success, Failure }

object Hello5 extends App {

  class A extends Actor {
    import context.dispatcher

    val f = Future("hello")

    def receive = {
      ???
    }

  }

  import scala.concurrent.ExecutionContext.Implicits.global

  val f = for {
    a <- Future(10 / 2) // 10 / 2 = 5
    b <- Future(a + 1) // 5 + 1 = 6
    c <- Future(a - 1) // 5 - 1 = 4
    if c > 3 // Future.filter
  } yield b * c // 6 * 4 = 24

  // Note that the execution of futures a, b, and c
  // are not done in parallel.
  f foreach println

  val future = Future("Hello")
  future onSuccess {
    case "bar" => println("Got my bar alright!")
    case x: String => println("Got some random string: " + x)
  }

  future onFailure {
    case ise: IllegalStateException if ise.getMessage == "OHNOES" =>
    //OHNOES! We are in deep trouble, do something!
    case e: Exception =>
    //Do something else
  }

  future onComplete {
    case Success(result) => doSomethingOnSuccess(result)
    case Failure(failure) => doSomethingOnFailure(failure)
  }
  
  def doSomethingOnSuccess(s: String) = println(s)
  def doSomethingOnFailure(e: Throwable) = e.printStackTrace()
  
}