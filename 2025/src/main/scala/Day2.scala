import scala.annotation.tailrec
import scala.io.StdIn

case class ProductIdRange(
    start: Long,
    end: Long
)

object ProductIdRange {
  def invalidIds(productIdRange: ProductIdRange): List[Long] =
    (productIdRange.start to productIdRange.end).filter(isInvalidId).toList

  def invalidIdsPart2(productIdRange: ProductIdRange): List[Long] =
    (productIdRange.start to productIdRange.end)
      .filter(isInvalidIdPart2)
      .toList
}

def parseProductIdRanges(input: String): List[ProductIdRange] = {
  input
    .split(",")
    .toList
    .map(_.split("-").map(_.toLong))
    .map(rarr => ProductIdRange(rarr(0), rarr(1)))
}

@tailrec
def decimalDigits(n: Long, laterDigits: List[Int] = List.empty): List[Int] = {
  if (n > 0) decimalDigits(n / 10, (n % 10).toInt :: laterDigits)
  else laterDigits
}

def isInvalidId(n: Long) = {
  val digits = decimalDigits(n)
  if (digits.length % 2 == 1) false
  else {
    digits.take(digits.length / 2) == digits.drop(digits.length / 2)
  }
}

def isInvalidIdPart2(n: Long) = {
  val digits = decimalDigits(n)

  @tailrec
  def isInvalidPart2Inner(chunkSize: Int): Boolean = {
    if (chunkSize == 0) false
    else if (
      (digits.length % chunkSize == 0) && (digits == List
        .fill(digits.length / chunkSize)(digits.take(chunkSize))
        .flatten)
    ) true
    else isInvalidPart2Inner(chunkSize - 1)
  }
  isInvalidPart2Inner(digits.length - 1)
}

@main def day2main(): Unit = {
  val rangeString = StdIn.readLine
  val sumOfInvalidIds = parseProductIdRanges(rangeString)
    .map(ProductIdRange.invalidIds)
    .map(_.sum)
    .sum
  println("Sum of invalid IDs: " + sumOfInvalidIds)

  val sumOfInvalidIdsPart2 = parseProductIdRanges(rangeString)
    .map(ProductIdRange.invalidIdsPart2)
    .map(_.sum)
    .sum
  println("Sum of invalid IDs for part 2: " + sumOfInvalidIdsPart2)
}
