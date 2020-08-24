import scala.collection.mutable

case class Vertex(name: Char, edges:Set[Vertex])
def dfsMutableRecursive(current: Vertex) = {
  val found = mutable.Set[Vertex]()
  def recurse(current: Vertex): Unit = {
    found += current
    for(next <- current.edges) {
      if(!found.contains(next)) {
        recurse(next)
      }
    }
  }
  recurse(current)
  found
}

/*
Complete this solution with fold looops.
val result = List.empty[Vertex].foldLeft(mutable.ListBuffer.empty[Vertex])((acc,e)=> {
  e.edges.toList match {
    case head :: tail => if(!acc.contains(head)) {

    } else acc += head
    case Nil => acc
  }

})*/

case class City(name: String)
case class Route(from: City, to: City, distance: Int)
