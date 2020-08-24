package ch6


trait RNG {
  type Rand[+A] = RNG => (A,RNG)
  def nextInt : (Int, RNG)

  def int: Rand[Int] = _.nextInt
  def unit[A](a:A):Rand[A] = {
    rng => (a, rng)
      // we have not given type for rng, it is automatically inferred from return type
  }
  def map[A,B](s:Rand[A])(f:A=>B):Rand[B] = {
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }
  }
  def map2[A,B,C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = rng => {
    val (a, r1) = ra(rng)
    val (b, r2) = rb(r1)
    (f(a, b), r2)
  }

  def both[A,B](ra: Rand[A], rb: Rand[B]): Rand[(A,B)] = map2(ra, rb)((_, _))

  def sequence[A](fs:List[Rand[A]]):Rand[List[A]] = {
    fs.foldLeft(unit(List[A]()))((acc, rng) => {
      map2(rng,acc)(_ :: _)
    })
  }
}

case class SimpleRNG(seed:Long) extends RNG {
  def nextInt : (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }

  def nonNegativeInt(rng:RNG):(Int, RNG) = {
    val (i, nextRNG) = rng.nextInt
    (if(i < 0)
      -(i+1)
    else i, nextRNG)
  }

  def double(rng:RNG):(Double, RNG) = {
    val (i, n) = nextInt
    (i/(Int.MaxValue.toDouble+1), n)
  }

  def intDouble(rng: RNG):((Int,Double), RNG) = {
    val (i, n1) = rng.nextInt
    val (d, n2) = double(n1)
    ((i,d),  n2)
  }

  def doubleInt(rng: RNG):((Double,Int), RNG) = {
    val ((i,d), next) = intDouble(rng)
    ((d,i),  next)
  }

  def double3(rng: RNG):((Double,Double,Double), RNG) = {
    val (d1, r1) = double(rng)
    val (d2, r2) = double(rng)
    val (d3, r3) = double(rng)
    ((d1, d2, d3), r3)
  }
  def ints(count:Int)(rng: RNG): (List[Int], RNG) = {
    (1 to count).foldLeft((List.empty[Int], rng))((tuple, e) => {
      val (list, oldRNG) = tuple
      val (i, newRNG) = oldRNG.nextInt
      (i :: list, newRNG)
    })
  }

  def nonNegativeEven: Rand[Int] = map(nonNegativeInt)(i => i - i%2)
  def double2: Rand[Double] = map(nonNegativeEven)(_/(Int.MaxValue.toDouble+1))

  val randIntDouble: Rand[(Int, Double)] = both(int,double)
  val randDoubleInt: Rand[(Double, Int)] = both(double,int)
}


object FunctionalState extends App {
  val rng  = SimpleRNG(99)
  println(rng.ints(5)(rng))

}
