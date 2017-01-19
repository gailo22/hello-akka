package hello.fib

import akka.actor._

class PrintlnActor extends Actor {
  def receive = {
    case msg => println("Received message '%s' in actor %s".format(msg, self.path.name))
  }
}