package collectionexamples

object Stack01 extends App {
  val stack = scala.collection.mutable.Stack[Int]()
  stack += 10
  stack += 9
  stack += 1

  println(stack.top)
  println(stack.pop)
  println(stack.pop)
  println(stack.pop)

  println("----------------------------------")
  // always use push for Stack behaviour
  stack.push(10)
  stack.push(9)
  stack.push(1)
  println(stack.pop)
  println(stack.pop)
  println(stack.pop)

}
