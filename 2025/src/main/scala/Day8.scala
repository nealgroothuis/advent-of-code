import scala.collection.mutable.ListBuffer
import scala.io.StdIn

case class Coordinate(x: Long, y: Long, z: Long)
case class Connection(distance: Double, i: Int, j: Int)

def findSortedConnections(coordinates: Array[Coordinate]): List[Connection] = {
  val n = coordinates.length
  (for {
    i <- 0 to n - 1
    j <- i + 1 to n - 1
  } yield {
    val a = coordinates(i)
    val b = coordinates(j)
    val distance =
      Math.sqrt(
        (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y) + (b.z - a.z) * (b.z - a.z)
      )
    Connection(distance, i, j)
  }).toList.sortBy(_.distance)
}

def findCircuits(lines: List[String], numConnections: Int): Set[Set[Int]] = {
  val coordinates = lines.map(parseInputLine)
  val sortedConnections = findSortedConnections(coordinates.toArray)
  val n = coordinates.length
  val circuits = (0 to (n - 1)).map(i => Set(i)).toArray
  sortedConnections
    .take(numConnections)
    .foreach(connection => {
      val iCircuit = circuits(connection.i)
      val jCircuit = circuits(connection.j)
      val newCircuit = iCircuit.union(jCircuit)
      newCircuit.foreach(i => circuits(i) = newCircuit)
    })
  circuits.distinct.toSet
}

def parseInputLine(line: String): Coordinate = {
  line.split(",").map(_.toLong).toList match {
    case x :: y :: z :: Nil => Coordinate(x, y, z)
    case _                  => ???
  }
}

@main def day8main(): Unit = {
  val lineBuffer = ListBuffer.empty[String]
  var line = StdIn.readLine()
  while (line != null) {
    lineBuffer += line.strip
    line = StdIn.readLine()
  }
  val lines = lineBuffer.toList

  val circuits = findCircuits(lines, 1000)
  val sortedCircuits = circuits.toList.sortBy(-_.size)
  val product3BiggestCircuitsizes =
    sortedCircuits.take(3).foldLeft(1)(_ * _.size)
  println(
    "Product of three biggest circuit sizes: " + product3BiggestCircuitsizes
  )
}
