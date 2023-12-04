import scala.util.matching.Regex

val digitPattern: Regex = "[0-9]".r
val digitWords = List("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
val wordsToDigits = digitWords.zipWithIndex.map { case (word, index) => word -> (index + 1)}.toMap
val backwordsToDigits = wordsToDigits.map(_.reverse -> _).toMap
val forwardDigitPattern = ("([0-9]|" + digitWords.mkString("|") + ")+?").r
val backwardDigitPattern = ("([0-9]|" + digitWords.map(_.reverse).mkString("|") + ")+?").r

def calibrationValue(line: String): Int = {
    val firstDigit = digitPattern.findFirstIn(line).get.toInt
    val lastDigit = digitPattern.findFirstIn(line.reverse).get.toInt
    firstDigit * 10 + lastDigit
}

def wordCalibrationValue(line: String): Int = {
    val firstMatch = forwardDigitPattern.findFirstIn(line).get
    val firstDigit = if (digitPattern.matches(firstMatch)) firstMatch.toInt else wordsToDigits(firstMatch)
    val lastMatch = backwardDigitPattern.findFirstIn(line.reverse).get
    val lastDigit = if (digitPattern.matches(lastMatch)) lastMatch.toInt else backwordsToDigits(lastMatch)
    firstDigit * 10 + lastDigit
}

@main def calculateCalibration(part: Int): Unit =
    part match
        case 1 =>
            println(scala.io.Source.stdin.getLines.map(calibrationValue).fold(0)(_ + _))
        case 2 =>
            println(scala.io.Source.stdin.getLines.map(wordCalibrationValue).fold(0)(_ + _))
