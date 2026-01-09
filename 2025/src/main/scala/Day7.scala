import scala.collection.mutable.ListBuffer
import scala.io.StdIn

def manifoldDiagram(lines: List[String]): List[(String, Int)] = {
  lines.tail
    .foldLeft(List((lines.head, 0)))((diagram, currentLine) => {
      val lastLine = diagram.head._1.toCharArray()
      val newLine = currentLine.toCharArray()
      var splits = 0
      for i <- 0 to lastLine.length - 1
      do {
        lastLine(i) match {
          case 'S' | '|' =>
            if (newLine(i) != '^') newLine(i) = '|'
            else {
              splits = splits + 1
              if (i > 0) newLine(i - 1) = '|'
              if (i < lastLine.length - 1) newLine(i + 1) = '|'
            }
          case _ => ()
        }
      }
      (newLine.mkString, splits) :: diagram
    })
    .reverse
}

def numPathsToPoint(lines: List[String]): List[List[Long]] = {
  lines.tail
    .foldLeft(
      List(lines.head.map {
        case 'S' => 1L
        case _   => 0
      }.toList)
    )((diagram, currentLine) => {
      val lastLine = diagram.head
      val newLine = Array.fill(lastLine.length)(0L)
      for i <- 0 to lastLine.length - 1
      do {
        if (currentLine(i) != '^') newLine(i) += lastLine(i)
        else {
          if (i > 0) newLine(i - 1) += lastLine(i)
          if (i < lastLine.length - 1) newLine(i + 1) += lastLine(i)
        }
      }
      newLine.toList :: diagram
    })
    .reverse
}

@main def day7main(): Unit = {
  val lineBuffer = ListBuffer.empty[String]
  var line = StdIn.readLine()
  while (line != null) {
    lineBuffer += line.strip
    line = StdIn.readLine()
  }
  val lines = lineBuffer.toList
  val numSplits = manifoldDiagram(lines).map(_._2).sum
  println("Number of splits: " + numSplits)

  val numTimelines = numPathsToPoint(lines).last.sum
  println("Number of timelines: " + numTimelines)
}
