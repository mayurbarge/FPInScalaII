package ch6combinator

import scala.util.Random
/*
trait RNG[+A] {
  def nextRNG: (A, RNG[A])
}
/*
case class RNG[Int] {
  def nextRNG: (Int, RNG[Int]) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }
}
*/

object RNG {
  type Rand[+A] = RNG[A] => (A, RNG[A])


}*/

object Combinators extends App {
  //println(RNG.intRand)
}