package list

trait MyList[+A]
case object MyNil extends MyList[Nothing]
case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A]
/*

object MyList {

  def apply[A](as: A*): MyList[A] =
    if (as.isEmpty) MyNil
    else Cons(as.head, apply(as.tail: _*))

}
object AppT extends App {
  val list = MyList[Nothing]
  println(MyNil)
}*/
