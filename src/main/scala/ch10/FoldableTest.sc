/*
Though it looks similar Functor and Foldable are not same
*/
trait Foldable[F[_]] {
  def foldRight[A,B](as: F[A])(z: B)(f: (A,B) => B): B
  def foldLeft[A,B](as: F[A])(z: B)(f: (B,A) => B): B
  def foldMap[A,B](as: F[A])(f: A => B)(mb: Monoid[B]): B
  def concatenate[A](as: F[A])(m: Monoid[A]): A =
  foldLeft(as)(m.zero)(m.op)
}

object FoldableList extends Foldable[List] {
  override def foldRight[A, B](as: List[A])(z: B)(f: (A, B) => B) = {
    as match {
      case Nil => z
      case h :: tail => foldRight(tail)(f(h))(f)
    }
  }

  override def foldLeft[A, B](as: List[A])(z: B)(f: (B, A) => B) = ???

  override def foldMap[A, B](as: List[A])(f: A => B)(mb: Monoid[B]) = ???
}