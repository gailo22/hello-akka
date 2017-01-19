package hello.fib

import akka.actor._
import scala.annotation.tailrec

case class FibonacciNumber(val nbr: Int)

class FibonacciActor extends Actor {
  
  def receive = {
    case FibonacciNumber(nbr) => sender ! fibonacci(nbr)
  }
  
  private def fibonacci(n: Int): Int = {
    
    @tailrec
    def fib(n: Int, b: Int, a: Int): Int = n match {
      case 0 => a
      case _ => fib(n-1, a+b, b)
    }
    
    fib(n, 1, 0)
  }
  
}