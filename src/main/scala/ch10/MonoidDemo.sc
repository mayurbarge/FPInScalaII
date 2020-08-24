trait Monoid[A] {
  def op(a1:A, a2: A): A
  def zero: A
}
val stringMonoid = new Monoid[String] {
  override def op(a1: String, a2: String) = a1 + a2
  override def zero = ""
}

def optionMonoid[A]: Monoid[Option[A]] = new Monoid[Option[A]] {
  def op(x: Option[A], y: Option[A]) = x orElse y
  val zero = None
}

def endoMonoid[A]: Monoid[A => A] = new Monoid[A => A] {
  override def op(f: A => A, g: A => A) = (a:A) => {
    // composing HOF ***IMP***
    (f andThen g)(a)
  }

  override def zero = (a:A) => a
}

def foldMap[A,B](as: List[A], m: Monoid[B])(f: A => B): B = {
  as.foldLeft(m.zero)((acc, e) => m.op(acc, f(e)))
}
def foldRight[A, B](as:List[A])(z: B)(f: (A, B) => B): B = {
  foldMap(as,endoMonoid[B])(f.curried)(z)
}

val optionMonoidInstance1 = optionMonoid[String].op(optionMonoid[String].op(None, Some("A")), Some("B"))
val optionMonoidInstance2 = optionMonoid[String].op(None, optionMonoid[String].op(Some("A"), Some("B")))

val words = List("Big","Rabbit")
words.foldLeft(stringMonoid.zero)(stringMonoid.op)

def foldMapV[A,B](v: IndexedSeq[A], m: Monoid[B])(f: A => B): B = {
  val  (left, right) = v.splitAt(v.length/2)
  (left.headOption, right.headOption) match {
    case (None, None) => m.zero
    case (Some(l), Some(r)) => m.op(f(l), f(r))
    case _=> m.op(foldMapV(left.tail, m)(f), foldMapV(right.tail, m)(f))
  }
}

val bird = IndexedSeq(9,1)
foldMapV[Int, String](bird,stringMonoid)((e:Int) => e.toString)
