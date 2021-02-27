package collectionexamples

object Queue01 extends App {
  val queue = scala.collection.mutable.Queue[Int]()
  queue += 1
  queue += 2
  queue += 3
  queue.enqueue(4)

  println(queue.dequeue())
  println(queue.dequeue())
  println(queue.dequeue())
  println(queue.dequeue())


}
