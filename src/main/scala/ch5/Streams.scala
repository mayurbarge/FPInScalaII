package ch5

sealed trait Stream[+A] {
  def map[B](f:A=>B):Stream[B] = {
    this match {
      case Empty => Empty
      case Cons(h,t) => {
        val head: A = h()
        val mapped = f(head)
        Cons[B](() => mapped, () => t().map(f))
      }
    }
  }

  /*
  getOrElse is not valid on lists
  def getOrElse[B >: A](default: B):B = {
    this match {
      case Empty => default
      case Cons(h,_) => h()
    }
  }*/

  def toList: List[A] = {
    this match {
      case Empty => List()
      case Cons(h,t) => h() :: t().toList
    }
  }

  def drop(n: Int): Stream[A] = {
    this match {
      case Empty => throw new Exception("List does not contain enough elements")
      case Cons(h,t) => {
        if(n == 0)
          this
        else Cons(() => h(), () => t().drop(n-1))
      }
    }
  }

  def take(n: Int): Stream[A] = {
    this match {
      case Empty => throw new Exception("List does not contain enough elements")
      case Cons(h,_) if(n == 1)=> Cons(() => h(), () => Empty)
      case Cons(h,t) => {
        Cons[A](() => h(),() => t().take(n-1))
      }
    }
  }

  def takeWhile(p: A => Boolean): Stream[A] = {
    this match {
      case Empty => throw new Exception("List does not contain enough elements")
      case Cons(h,_) if !p(h()) => Cons(() => h(), () => Empty)
      case Cons(h,t) => {
        Cons[A](() => h(),() => t().takeWhile(p))
      }
    }
  }

  // this does not work as tail is not executed inside map it returns first head element only and runs exists on it
  //def exists(p : A =>Boolean):Boolean = this.map(p).getOrElse(false)
  def exists(p : A =>Boolean):Boolean = {
    this match {
      case Cons(h, t) => p(h()) || t().exists(p)
      case _ => false
    }
  }
  def exists1(p : A =>Boolean):Boolean = foldRight(false)((a,b) => p(a) || b)

  def foldRight[B](z: =>B)(f:(A, => B) => B): B = {
    this match {
      case Empty => z
      case Cons(h,t) => f(h(), t().foldRight(z)(f))
    }
  }

  def forall(p : A => Boolean):Boolean = foldRight(true)((e,acc) => p(e) && acc)
  def takeWhile1(p: A => Boolean): Stream[A] = foldRight[Stream[A]](Empty)((h,t) => if(p(h)) Cons(() => h,() => t.takeWhile1(p)) else Empty)
  def headOption:Option[A] = foldRight(None: Option[A])((e,_) => Some(e))

}
final case class Cons[+A](head:()  => A, tail:() => Stream[A]) extends Stream[A]
final case object Empty extends Stream[Nothing]

object Stream {
  def cons[A](head: => A, tail: => Stream[A]):Stream[A] = {
    lazy val h = head
    lazy val t = tail
    Cons(() => h, () => t)
  }
  def empty[A]: Stream[A] = Empty
  def apply[A](as : A*): Stream[A] = {
    /*if(as.isEmpty)
      Empty
    else Cons(() => as.head, () => apply(as.tail: _*)) */
    if(as.isEmpty)
      empty
    else cons(as.head, apply(as.tail: _*)) // _ bottom
  }
  def constant[A](a: A):Stream[A] = Stream.cons(a, constant(a))
  def from(n: Int):Stream[Int] = Stream.cons(n, from(n+1))
  def fibs = {
    def go(f0:Int, f1:Int): Stream[Int] = {
      Stream.cons(f0, go(f1,f0+f1))
    }
    go(0,1)
  }
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = {
    f(z) match {
      case None => empty
      case Some((a,s)) => cons(a, unfold(s)(f))
    }
  }
  def fibs1 = unfold(0)(acc => {
    if(acc == 0) None
    else Some(acc, acc+1)
  })

}

object StreamsTest extends App {
  val stream: Stream[Int] = Cons(() => 1 , () => Cons(() => 2, ()=> Empty))
  val mapped  = stream.map((e:Int) => "---" + e.toString)
  mapped.map((e:String) => println(e))
  println(stream.toList)
  println(stream.drop(1).toList)
  println(stream.take(1).toList)
  println(stream.takeWhile((e:Int) => e < 2).toList)
  println("~~~~~~~~~~~~~~~~~~~~~~~")
  val stream2: Stream[Int] = Cons(() => 1 , () => Cons(() => 1, ()=> Cons(() => 4, ()=> Empty)))
  println(stream2.forall((e:Int) => e == 1))
  println(stream2.takeWhile1((e:Int) => e == 1).toList)
  println(stream2.headOption)
  //println(stream.exists((e:Int) => e == 2))

  println("Infinite streams-----------")
  val ones: Stream[Int] =  Stream.cons(1, ones)
  println(ones.take(5).toList)
  println(Stream.constant(1).take(5).toList)
  println(Stream.from(1).take(5).toList)
  println(Stream.fibs.take(3).toList)

  println(ones.map(x=>x).take(10).toList)

  println("Stream building")
  println(Stream.unfold(0)(e => Option((e.toString, e+1))).take(10).toList)
  println(Stream.unfold(2.toLong)(e => Option((e.toString, e*e))).take(10).toList)
  println(Some(4294967296l*4294967296l))
  println(Stream.unfold(5)(e => Option((100/e, e-1))).take(5).toList) // take 6 results in exception
  //println(Stream.unfold(5)(e => None).take(5).toList) // throw list does not contain enough element exception

  println("unfold examples")
  println(Stream.fibs1.take(5).toList)

}
