import scala.collection.mutable.ListBuffer
import scala.io.StdIn
import scala.annotation.tailrec
case class FreshIDRange(bottom: Long, top: Long)

object FreshIDRange {
  def isInRange(range: FreshIDRange)(id: Long) = {
    id >= range.bottom && id <= range.top
  }

  def numIDsInRange(range: FreshIDRange): Long = range.top - range.bottom + 1L
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

@tailrec
def countNumFreshIDs(
    rangesToCount: List[FreshIDRange],
    countedRanges: List[FreshIDRange] = List.empty,
    count: Long = 0
): Long = {
  rangesToCount match {
    case rangeToCount :: rest =>
      val newFreshIDs = rangeToCount.bottom
        .to(rangeToCount.top)
        .toList
        .count(!isInAnyRange(countedRanges)(_))
      countNumFreshIDs(rest, rangeToCount :: countedRanges, count + newFreshIDs)
    case Nil => count
  }
}

def unionRanges(freshIDRanges: List[FreshIDRange]): List[FreshIDRange] =
  unionSortedRanges(freshIDRanges.sortBy(_.bottom))

@tailrec
def unionSortedRanges(
    sortedFreshIDRanges: List[FreshIDRange],
    disjointRanges: List[FreshIDRange] = List.empty
): List[FreshIDRange] = {
  sortedFreshIDRanges match {
    case Nil         => disjointRanges
    case head :: Nil => head :: disjointRanges
    case head :: next :: rest if next.bottom > (head.top + 1) =>
      unionSortedRanges(next :: rest, head :: disjointRanges)
    case head :: next :: rest =>
      unionSortedRanges(
        FreshIDRange(head.bottom, head.top.max(next.top)) :: rest,
        disjointRanges
      )
  }
}

@main def day5main(): Unit = {
  val rangeLineBuffer = ListBuffer.empty[String]
  var rangeLine = StdIn.readLine()
  while (rangeLine != "") {
    rangeLineBuffer += rangeLine.strip
    rangeLine = StdIn.readLine()
  }
  val freshIDRanges = parseFreshIDRanges(rangeLineBuffer.toList)
  println("Number of fresh ID ranges: " + freshIDRanges.length)

  val availableIDLineBuffer = ListBuffer.empty[String]
  var availableIDLine = StdIn.readLine()
  while (availableIDLine != null) {
    availableIDLineBuffer += availableIDLine.strip
    availableIDLine = StdIn.readLine()
  }
  val availableIDs = availableIDLineBuffer.toList.map(_.toLong)
  val numIDsInAnyRange = availableIDs.count(isInAnyRange(freshIDRanges))
  println("Number of IDs in any range: " + numIDsInAnyRange)

  val numFreshIDs = unionRanges(freshIDRanges).map(FreshIDRange.numIDsInRange).sum
  println("Number of fresh IDs: " + numFreshIDs)
}
