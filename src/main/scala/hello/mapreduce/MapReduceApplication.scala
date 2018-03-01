package hello.mapreduce

import akka.actor.ActorSystem
import akka.actor.Props


class Word(val word: String, val count: Int)
case class Result()
class MapData(val dataList: List[Word])
class ReduceData(val reduceDataMap: Map[String, Int])

object MapReduceApplication {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("MapReduceApp")
    val master = system.actorOf(Props[MasterActor], name = "master")
    
    master ! "Hello world application"
    master ! "The best way to send a message from akka actor"
    master ! "Please say hello and give a hi"
    
    Thread.sleep(500)
    
    master ! new Result
    
    Thread.sleep(500)
    system.terminate()
    println("done!")
    
  }
}