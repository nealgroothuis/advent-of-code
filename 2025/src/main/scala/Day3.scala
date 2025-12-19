import scala.collection.mutable.ListBuffer
import scala.io.StdIn
import scala.annotation.tailrec

case class HighestJoltage(
    mostSignificant: Int,
    leastSignificant: Int
)

object HighestJoltage {
  def joltage(highestJoltage: HighestJoltage) =
    highestJoltage.mostSignificant * 10 + highestJoltage.leastSignificant
}

@tailrec
def highestJoltageRecur(
    highestJoltage: HighestJoltage,
    joltages: List[Int]
): HighestJoltage = {
  joltages match {
    case a :: b :: rest if a > highestJoltage.mostSignificant =>
      highestJoltageRecur(HighestJoltage(a, b), b :: rest)
    case a :: rest if a > highestJoltage.leastSignificant =>
      highestJoltageRecur(
        HighestJoltage(highestJoltage.mostSignificant, a),
        rest
      )
    case _ :: rest => highestJoltageRecur(highestJoltage, rest)
    case _         => highestJoltage
  }
}

def highestJoltage(joltages: List[Int]): HighestJoltage = {
  joltages match {
    case firstJoltage :: secondJoltage :: remainingJoltages =>
      highestJoltageRecur(
        HighestJoltage(firstJoltage, secondJoltage),
        secondJoltage :: remainingJoltages
      )
    case _ => ???
  }
}

val numBatteriesDay2 = 12

@tailrec
def highestStartingSubstringAtLeastNLong(
    n: Int,
    joltages: List[Int],
    highestStartingSubstring: List[Int]
): List[Int] = {
  if (joltages.length < n) highestStartingSubstring
  else if (joltages.head > highestStartingSubstring.head)
    highestStartingSubstringAtLeastNLong(n, joltages.tail, joltages)
  else
    highestStartingSubstringAtLeastNLong(
      n,
      joltages.tail,
      highestStartingSubstring
    )
}

@tailrec
def highestJoltageDay2(
    joltages: List[Int],
    fixedJoltages: List[Int] = List.empty
): List[Int] = {
  if (fixedJoltages.length == numBatteriesDay2) fixedJoltages.reverse
  else {
    val newStuff = highestStartingSubstringAtLeastNLong(
      numBatteriesDay2 - fixedJoltages.length,
      joltages,
      joltages
    )
    highestJoltageDay2(newStuff.tail, newStuff.head :: fixedJoltages)
  }
}

@main def day3main(): Unit = {
  val lines = ListBuffer.empty[String]
  var line = StdIn.readLine()
  while (line != null) {
    lines += line
    line = StdIn.readLine()
  }
  val totalHighestJoltages =
    lines
      .map(line => line.map(_.asDigit).toList)
      .map(highestJoltage)
      .map(hjs => hjs.mostSignificant * 10 + hjs.leastSignificant)
      .sum
  println(
    "Sum of highest possible joltages from each bank: " + totalHighestJoltages
  )

  val totalHighestJoltagesDay2: BigInt =
    lines.toList
      .map(line => line.map(_.asDigit).toList)
      .map(joltages => highestJoltageDay2(joltages))
      .map(_.map(_.toString).mkString)
      .map(BigInt.apply)
      .sum

  println(
    "Sum of highest possible joltages from each bank for day 2: " + totalHighestJoltagesDay2
  )
}
