import scala.annotation.tailrec
import scala.io.StdIn

case class ProductIdRange(
    start: Long,
    end: Long
)

object ProductIdRange {
  def invalidIds(productIdRange: ProductIdRange): List[Long] =
    (productIdRange.start to productIdRange.end).filterNot(isValidId).toList
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

def isValidId(n: Long) = {
  val digits = decimalDigits(n)
  if (digits.length % 2 == 1) true
  else {
    digits.take(digits.length / 2) != digits.drop(digits.length / 2)
  }
}

@main def day2main(): Unit = {
  val sumOfInvalidIds = parseProductIdRanges(StdIn.readLine)
    .map(ProductIdRange.invalidIds)
    .map(_.sum)
    .sum
  println("Sum of invalid IDs: " + sumOfInvalidIds)
}
