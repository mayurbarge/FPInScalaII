
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
  trait Functor[F[_]] {
    type ->[A,B] = A =>  B
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


