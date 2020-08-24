class State[S, +A](runS: S => (A, S)) {
  def map[B](f: A => B): State[S,B] = {
    new State[S,B](s1 => {
      val (a1, s2) = runS(s1)
      (f(a1), s2)
    })
  }

  def flatMap[B](f: A => State[S,B]): State[S,B] = {
    new State[S,B](s1 => {
      val (a1, s2) = runS(s1)
      f(a1) runS s2
    })
  }
}