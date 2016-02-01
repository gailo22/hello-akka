package hello.calculator

import akka.actor.TypedActor
import scala.concurrent.Promise
import scala.concurrent.Future
import akka.actor.TypedProps
import akka.actor.ActorSystem
import scala.concurrent.Await
import scala.concurrent.duration._

class Calculator extends CalculatorInt {
  var counter: Int = 0
  
  import TypedActor.dispatcher
  
  def add(first: Int, second: Int): Future[Int] =
    Promise successful first + second future
    
  def subtract(first: Int, second: Int): Future[Int] =
    Promise successful first - second future
    
  def incrementCount(): Unit = counter += 1
  
  def incrementAndReturn(): Option[Int] = {
    counter += 1
    Some(counter)
  }
}

object App {
  
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("TypedActorsExample")
    val calculator1: CalculatorInt =
      TypedActor(system).typedActorOf(TypedProps[Calculator]())
      
      
    calculator1.incrementCount()
    
    val future = calculator1.add(14, 6)
    val result = Await.result(future, 5 second)
    
    println(result)
    
    TypedActor(system).stop(calculator1)
//    TypedActor(system).poisonPill(calculator1)
  }
  
  
}