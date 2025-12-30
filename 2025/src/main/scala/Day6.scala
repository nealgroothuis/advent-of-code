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

def answerProblemsPart2(lines: List[String]): List[Long] = {
  val numberLines = lines.take(lines.length - 1).map(_.toCharArray.toList)
  val operatorLine = lines.last
  val transposedNumberLines = transpose(numberLines).map(_.mkString.strip)
  val (ll, l) = transposedNumberLines.foldLeft(
    (List.empty[List[Long]], List.empty[Long])
  )((lll, s) => {
    val (ll, l) = lll
    s match {
      case "" => (l :: ll, List.empty[Long])
      case _  => (ll, s.toLong :: l)
    }
  })
  val problems = l :: ll
  val operatorRow = operatorLine.split("\\s+").toList.reverse
  answerProblems(problems, operatorRow)
}

def transpose[T](rows: List[List[T]]): List[List[T]] = {
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
      parseRows(
        rest,
        numberRow.split("\\s+").toList.map(_.toLong) :: parsedRows
      )
  }
}

@main def day6main(): Unit = {
  val lineBuffer = ListBuffer.empty[String]
  var line = StdIn.readLine()
  while (line != null) {
    lineBuffer += line.strip
    line = StdIn.readLine()
  }
  val lines = lineBuffer.toList
  val (rows, operators) = parseRows(lines)
  val cols = transpose(rows)
  val answers = answerProblems(cols, operators)
  println("Sum of answers: " + answers.sum)

  val answersPart2 = answerProblemsPart2(lines)
  println("Sum of answers (part 2): " + answersPart2.sum)
}
