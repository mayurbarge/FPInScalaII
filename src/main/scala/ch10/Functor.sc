
trait Functor[F[_]] {
  type ->[A,B] = A =>  B
  type $$[A,B] = F[A] => F[B]
  def fmap[F,A,B](f: ->[A,B]): $$[A, B]
}

val intListFunctorToStringListFunctor = new Functor[List] { self =>
  override def fmap[List,Int, String](f: ->[Int,String] ): $$[Int,String] = {
    self => {
      self.map(f(_))
    }
  }
}
val str: Int => String = (i:Int) => "---" + i.toString + "---"
val mapping = intListFunctorToStringListFunctor.fmap[List[_],Int,String](str)
mapping(List(1,2,3,4))

object ScalaVersion {
  trait Functor[F[_]] { self =>
    type ->[A,B] = A => B
    type $$[A,B] = F[A] => F[B]
    def fmap[A,B](f: ->[A,B]): $$[A, B]
  }
  val intListFunctorToStringListFunctor = new Functor[List] { self =>
    override def fmap[A, B](f: A -> B): A $$ B = self => self.map(f)
  }
  val str: Int => String = (i:Int) => "-^^-" + i.toString + "-^^-"
  val mapping = intListFunctorToStringListFunctor.fmap[Int,String](str)

  val optionFunctor = new Functor[Option] { self =>
    override def fmap[A, B](f: A -> B): A $$ B = self => self.map(f)
  }
}
ScalaVersion.mapping(List(1,2,3))
val toIntF = (i:String) => i.toInt
ScalaVersion.optionFunctor.fmap[String,Int](toIntF)(Some("100"))
/*val (stringOpt, intOpt) = ScalaVersion.optionFunctor.distribute[String,Int](("1",1))
stringOpt
intOpt*/


object FunctorInBook {
  trait FunctorInBook[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]

    def distribute[A, B](fab: F[(A, B)]): (F[A], F[B]) = {
      /*
        Let FT = F[(A,B)] and V = (F[A], F[B]) then expansion for each value of tuple is as
        [T,V] map(F[T]) {T=>V}
       */
      (map(fab) {
        e => {
          val x: (A, B) = e
          e._1
        }
      }, map(fab)(_._2))
    }
  }

  /*
  Due to type inference we can not implement distribute right awary as done in FunctorInBook
  Instead only option remains is this way
   */
  trait Functor1[F[_]] {
    def fmap[A, B](f: A => B): F[A] => F[B]
    // distribute can not unwrap contents inside F if you try to implement it here, no map found on F
    def distribute[A, B](fab: F[(A, B)]): (F[A], F[B])
  }

  val squareRootFunctor1 = new Functor1[Option] { self =>
    override def fmap[Int, Double](f: Int => Double): Option[Int] => Option[Double] = {
      self => self.map(f)
    }

    override def distribute[Int, Double](fab: Option[(Int,Double)]): (Option[Int], Option[Double]) = {
       (fab.map(_._1),fab.map(_._2))
    }
  }

  trait Functor2[F[_]] {
    def fmap[A, B](f: A => B): F[A] => F[B]
    def distribute[A, B](): F[(A,B)] => (F[A], F[B])
  }

  val squareRootFunctor2: Functor2[Option] = new Functor2[Option] { self =>
    override def fmap[Int, Double](f: Int => Double): Option[Int] => Option[Double] = {
      self => self.map(f)
    }

    override def distribute[Int, Double](): Option[(Int, Double)] => (Option[Int], Option[Double]) = { self =>
      (self.map(_._1),self.map(_._2))
    }
  }
}

val  (fa, fb) = FunctorInBook.squareRootFunctor1.distribute(Some(2,4))
val (fx,fy) = FunctorInBook.squareRootFunctor2.distribute()(Some(4,16))

val f = (a: Int) => a.toString
val fa = f(10)
val Ff: Option[Int => String] = Option(f)
