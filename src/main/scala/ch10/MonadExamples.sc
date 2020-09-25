object M {

  trait Monad[F[_]] {
    def unit[A](f: => A): F[A]

    def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]
  }

  case class State[S, A](run: S => (A, S)) {
    def map[B](f: A => B): State[S, B] = {
      State(s => {
        val (a, s1) = run(s)
        (f(a), s1)
      })
    }

    def flatMap[A, B](f: A => State[S, B]): State[S, B] = {
      State(s => {
        val (a, s1) = run(s)
        f(a).run(s1)
      })
    }
  }

  def stateMonad[S] = new Monad[({ type f[x] = State[S, x] })#f]{
    override def unit[A](a: => A) = State(s => (a,s))
    override def flatMap[A, B](ma: State[S, A])(f: A => State[S, B]) =
      ma flatMap f
  }

  val F = stateMonad[Int]

}