package hello.mapreduce

import akka.actor.Actor
import akka.actor.ActorRef
import scala.collection.immutable.HashMap

class ReduceActor(aggregateActor: ActorRef) extends Actor {

  val defaultCount: Int = 1
  
  def receive: Receive = {
    case message: MapData =>
      aggregateActor ! reduce(message.dataList)
  }
  
  def reduce(dataList: List[Word]): ReduceData = {
    var reduceMap = new HashMap[String, Int]
    for (wc: Word <- dataList) {
      var word: String = wc.word
      if (reduceMap.contains(word)) {
        var count: Int = reduceMap.get(word).get + defaultCount
        reduceMap += word -> count
      } else {
        reduceMap += word -> defaultCount
      }
    }
    new ReduceData(reduceMap)
  }
  
}