/*
package test

import scala.io.StdIn

object AppT extends App {
  case class Node(number: Int, priority: Int)
  case class Edge(node1: Node, node2: Node, distance: Int)


  override def main(args: Array[String]): Unit = {

    val nodesEdges = StdIn.readLine().split(" ").map(_.toInt)
    val (nodeNumbers, totalEdges) = (nodesEdges.head + 1, nodesEdges.last)
    val priorities = StdIn.readLine().split(" ").map(_.toInt)

    val nodes = ((0 to nodeNumbers) zip (Int.MaxValue +: priorities)).map(nodePriority => {
      val (nodeNumber, priority) = nodePriority
      Node(nodeNumber, priority)
    })

    println(nodes)
    val edges  = (1 to totalEdges).map(e => {
      val edgeInput = StdIn.readLine().split(" ").toList
      edgeInput match {
        case first :: second :: distance  :: Nil => {
          println(first + " " + second + " " + distance)
          Edge(nodes.find(first.toInt == _.number).get, nodes.find(second.toInt == _.number).get, distance.toInt)
        }
        case _=> throw new Exception("invalid")
      }
    })

    println(edges)

    val sorted = nodes.sortWith(_.priority > _.priority).toList
    val grouped = sorted.groupBy(_.priority)
   /* grouped.foldLeft(None,0, List.empty)((acc, priorityGroups) => {

      val (priority,groups) = priorityGroups
      val (currentNode, currentDistance, visitedNodes) = acc



      currentNode match {
        case None => (groups.g)
      }

      (None, 0, List.empty)
    })*/
      /*val (priority,groups) = priorityGroups
      val (currentNode, currentDistance, visitedNodes) = acc

    }))*/

    process(sorted, edges.toList)

  }

  def process(nodes: List[Node], edges: List[Edge]) = {


    def loop(grouped:Seq[(Int, List[Node])], visitedNodes: List[Node], currentDistance: Int, distances: List[Int]) = {
      if(visitedNodes.size == nodes.size) {
        distances
      } else {
        val (priority, nodesWithPriority) = grouped.head
        if(visitedNodes.isEmpty) {
          visitedNodes ++ nodesWithPriority.head
        } else {

        }
        val lastNode = visitedNodes.last


        //val distance = currentDistance +

      }
    }
    val distance = (0 to nodes.size-1).map(e => 0).toList
    loop(nodes.groupBy(_.priority).toList, List.empty,0,  distance)


  }
}
*/
