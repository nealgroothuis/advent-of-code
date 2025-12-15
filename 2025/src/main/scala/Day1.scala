import scala.io.StdIn
import scala.collection.mutable.ListBuffer

case class PositionAndNumClicksEndingOnZero(
    position: Int,
    numClicksEndingOnZero: Int
)

def nextPositionAndNumClicksEndingOnZero(
    position: Int,
    numClicks: Int
): PositionAndNumClicksEndingOnZero = {
  val absoluteNewPosition = position + numClicks
  val newPosition = Math.floorMod(absoluteNewPosition, 100)
  val numClicksEndingOnZero =
    if (numClicks == 0) 0
    else if (absoluteNewPosition > 0) Math.floorDiv(absoluteNewPosition, 100)
    else
      Math.abs(
        Math.floorDiv(absoluteNewPosition - 1, 100)
      ) - (if (position == 0) 1 else 0)
  PositionAndNumClicksEndingOnZero(newPosition, numClicksEndingOnZero)
}

def positionsAndNumClicksEndingOnZero(
    clicks: List[Int]
): List[PositionAndNumClicksEndingOnZero] = {
  clicks
    .foldLeft(List(PositionAndNumClicksEndingOnZero(50, 0)))(
      (ps: List[PositionAndNumClicksEndingOnZero], c: Int) =>
        nextPositionAndNumClicksEndingOnZero(ps.head.position, c) :: ps
    )
    .reverse
}

def clicks(lines: List[String]): List[Int] = {
  lines.map { line =>
    line.head match {
      case 'L' => -line.tail.toInt
      case 'R' => line.tail.toInt
    }
  }
}

@main def main(): Unit = {
  val lines = ListBuffer.empty[String]
  var line = StdIn.readLine()
  while (line != null) {
    lines += line
    line = StdIn.readLine()
  }
  val pncs = positionsAndNumClicksEndingOnZero(clicks(lines.toList))
  println("Dial ends on 0: " + pncs.count(_.position == 0))
  println(
    "Dial click ends on 0: " + pncs.foldLeft(0)(_ + _.numClicksEndingOnZero)
  )
}
