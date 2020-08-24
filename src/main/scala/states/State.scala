package states

case class State(value: Int) {

  def flatMap(f: Int => State): State = {
    val newState = f(value)
    State(newState.value)
  }

  def map(f: Int => Int): State = State(f(value))
}

object StateExample extends App {
  val result: State = for {
    a <- State(20)
    b <- State(a+15)

  } yield {
    b
  }

  /*
  for(x <- c1; y <- c2; z <- c3) yield {...}
  c1.flatMap(x => c2.flatMap(y => c3.map(z => {...})))


  // The following ...
for {
  bound <- list
  out <- f(bound)
} yield out

// ... is translated by the Scala compiler as ...
list.flatMap { bound =>
  f(bound).map { out =>
    out
  }
}
   */

  println(result)
}