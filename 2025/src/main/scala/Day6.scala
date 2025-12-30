import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.io.StdIn

def answerProblems(
    cols: List[List[Long]],
    operatorRow: List[String]
): List[Long] = {
  val operators = operatorRow.map {
    case "+" => (a: Long, b: Long) => a + b
    case "*" => (a: Long, b: Long) => a * b
    case _   => ???
  }
  cols
    .zip(operators)
    .map((numbers, operator) => numbers.tail.fold(numbers.head)(operator))
}

def transpose(rows: List[List[Long]]): List[List[Long]] = {
  rows.tail
    .foldLeft(rows.head.map(i => List(i)))((acc, row) =>
      row.zip(acc).map(_ :: _)
    )
    .map(_.reverse)
}

@tailrec
def parseRows(
    rows: List[String],
    parsedRows: List[List[Long]] = List.empty
): (List[List[Long]], List[String]) = {
  rows match {
    case Nil                => ???
    case operatorRow :: Nil =>
      (parsedRows.reverse, operatorRow.split("\\s+").toList)
    case numberRow :: rest =>
      parseRows(rest, numberRow.split("\\s+").toList.map(_.toLong) :: parsedRows)
  }
}

@main def day6main(): Unit = {
  val lineBuffer = ListBuffer.empty[String]
  var line = StdIn.readLine()
  while (line != null) {
    lineBuffer += line.strip
    line = StdIn.readLine()
  }
  val (rows, operators) = parseRows(lineBuffer.toList)
  val cols = transpose(rows)
  val answers = answerProblems(cols, operators)
  println("Sum of answers: " + answers.sum)
}
