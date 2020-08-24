
val intAddition: Monoid[Int] = new Monoid[Int] {
  override def op(a1: Int, a2: Int) = a1 + a2
  override def zero = 0
}