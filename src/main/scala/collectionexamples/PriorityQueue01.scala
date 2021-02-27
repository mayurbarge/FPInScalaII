package collectionexamples

object PriorityQueue01 extends App {
  case class Fruit(weight: Int)

  case class Fish(weight: Int) extends Ordered[Fish] {
    override def compare(that: Fish): Int = this.weight compare that.weight
  }

  val queue = scala.collection.mutable.PriorityQueue[Fruit]()(Ordering.by(fruit => fruit.weight))
  queue += Fruit(1)
  queue += Fruit(9)
  queue += Fruit(12)
  println(queue.clone().dequeueAll)

  val fishQueue = scala.collection.mutable.PriorityQueue[Fish]()
  fishQueue += Fish(2000)
  fishQueue += Fish(20)
  fishQueue += Fish(200)
  println(fishQueue.reverse.clone().dequeueAll)

}
