package collectionexamples



object Ex1 extends App {
  def solution(s: String): Int = {
    // write your code in Scala 2.12
    // c 3, a 2, f2 d 2 e 3
    //

    val N = s.length

    //var start = 0
    //var end = 0
    //var result = Array[Int]()
    var indexCounts = scala.collection.mutable.Map[Char, Int]()
    var modificationsCount = 0
    //s.toCharArray.groupBy(e => e.charValue()).map(e => (e._1, e._2.length))
    println("##############-------")
    for(i <- 0 to N-1) {
      println("##############")
      println(indexCounts.toList)
      indexCounts.get(s.charAt(i)) match {
        case Some(count) => {
          if(indexCounts.values.toList.size == 1) {
            modificationsCount += 1
            indexCounts += (s.charAt(i) -> (count+1))
          }
          if(indexCounts.values.toList.size > 1 &&
            !indexCounts.values.toList.contains(count+1)) {
            modificationsCount += 1
            indexCounts += (s.charAt(i) -> (count+1))
          }
        }
        case None => {
          modificationsCount += 1
          indexCounts += (s.charAt(i) -> 1)
        }
      }
    }
    modificationsCount
  }
  solution("ccaaffddecee")

}

