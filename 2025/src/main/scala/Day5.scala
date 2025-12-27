import scala.collection.mutable.ListBuffer
import scala.io.StdIn
case class FreshIDRange(bottom: Long, top: Long)

object FreshIDRange {
  def isInRange(range: FreshIDRange)(id: Long) = {
    id >= range.bottom && id <= range.top
  }
}

def isInAnyRange(ranges: List[FreshIDRange])(id: Long): Boolean = {
  ranges.exists(FreshIDRange.isInRange(_)(id))
}

def parseFreshIDRanges(strings: List[String]): List[FreshIDRange] = {
  strings.map(string => {
    val rangeArray = string.split("-")
    FreshIDRange(rangeArray(0).toLong, rangeArray(1).toLong)
  })
}

@main def day5main(): Unit = {
  val rangeLineBuffer = ListBuffer.empty[String]
  var rangeLine = StdIn.readLine()
  while (rangeLine != "") {
    rangeLineBuffer += rangeLine.strip
    rangeLine = StdIn.readLine()
  }
  val freshIDRanges = parseFreshIDRanges(rangeLineBuffer.toList)

  val availableIDLineBuffer = ListBuffer.empty[String]
  var availableIDLine = StdIn.readLine()
  while (availableIDLine != null) {
    availableIDLineBuffer += availableIDLine.strip
    availableIDLine = StdIn.readLine()
  }
  val availableIDs = availableIDLineBuffer.toList.map(_.toLong)
  val numIDsInAnyRange = availableIDs.count(isInAnyRange(freshIDRanges))
  println("Number of IDs in any range: " + numIDsInAnyRange)
}
