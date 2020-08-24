package Trampolines

import java.io._
import java.util._

import scala.collection.immutable._
import scala.io._



object Result {


  def isPrime(n: Long): Int = {
    def recurse(number: Int, factors: ) = {

    }

    isPrime(n, )

  }

}

object Solution {
  def main(args: Array[String]) {

    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val n = StdIn.readLine.trim.toLong

    val result = Result.isPrime(n)

    printWriter.println(result)

    printWriter.close()
  }
}