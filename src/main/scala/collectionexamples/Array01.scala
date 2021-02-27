package collectionexamples

import scala.collection.mutable.ArrayBuffer

object Array01 extends App {
  println("Array object")
  println("*"*30)
  println(Array.fill(10)(11).toList)
  val twoD = Array.ofDim[Int](2,2)
  twoD(0)(0) = 1
  println(twoD.toList)
  println(Array.range(0, 12, 3).toList)
  Array.tabulate(4,4)((a,b) =>a*b).toList.map(e => print(e.toList + " "))

  println("ArraySeq")
  println("*"*30)
  println("Prepended")
  val arr = Array(1,2,3,4,5,6)
  val arr1 = 0 +: arr // same as prepended
  val arr2 = Array(-1,-2) ++: arr1 // same as prependAll
  val arr3 = arr prependedAll Array[Int](-1,-2)
  println(arr.toList)
  println(arr1.toList)
  println(arr2.toList)
  println(arr3.toList)
  // appended
  val arr4 = arr :+ 7
  val arr5 = arr4 :++ Array(8,9)
  println("Appended")
  println(arr4.toList)
  println(arr5.toList)
  val map = Map.empty[Int, Int]
  val map1 = arr5 collect map
  val even = arr5 collect { case e if (e%2 == 0) => e }
  println(map1.toList)
  println(even.toList)
  println("Combinations")
  val comb = Array(1,2,3).combinations(2).toList
  val perm = Array(1,2,3).permutations.toList
  comb.map(e => {
    println()
    e.map(x => print(x + " "))
  })
  println("Permutations")
  perm.map(e => {
    println()
    e.map(x => print(x + " "))
  })
  println("\n" + (Array(1,2,3) diff Array(3)).toList)
  println("Drop")
  println((Array(1,2,3) drop 2).toList)
  println("DropRight")
  println((Array(1,2,3) dropRight 1).toList)
}
