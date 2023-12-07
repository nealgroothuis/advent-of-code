import scala.util.matching.Regex

val gameAndDrawsPattern: Regex = "Game ([0-9]*): *(.*)".r
val redPattern: Regex = " *([0-9]*) red *".r
val greenPattern: Regex = " *([0-9]*) green *".r
val bluePattern: Regex = " *([0-9]*) blue *".r

case class Draw(numRed: Int, numGreen: Int, numBlue: Int)
case class Game(id: Int, draws: List[Draw])

def parseLine(line: String) = line match {
  case gameAndDrawsPattern(id, draws) =>
    Game(
      id = id.toInt,
      draws = draws
        .split(";")
        .toList
        .map(
          _.split(",").foldLeft(Draw(numRed = 0, numBlue = 0, numGreen = 0))(
            (draw, color) =>
              color match {
                case redPattern(numRed) => draw.copy(numRed = numRed.toInt)
                case greenPattern(numGreen) =>
                  draw.copy(numGreen = numGreen.toInt)
                case bluePattern(numBlue) => draw.copy(numBlue = numBlue.toInt)
              }
          )
        )
    )
}

@main def calculateCalibration(): Unit = {
  val games = scala.io.Source.stdin.getLines.map(parseLine).toList;
  val goodGames = games.filter(
    _.draws.forall(draw =>
      draw.numRed <= 12 && draw.numGreen <= 13 && draw.numBlue <= 14
    )
  )
  println(s"Sum of valid game IDs: ${goodGames.map(_.id).sum}")
  val minimumDraws = games.map(
    _.draws.foldLeft(Draw(numRed = 0, numBlue = 0, numGreen = 0))(
      (minDraw, draw) =>
        Draw(
          numRed = minDraw.numRed.max(draw.numRed),
          numBlue = minDraw.numBlue.max(draw.numBlue),
          numGreen = minDraw.numGreen.max(draw.numGreen)
        )
    )
  )

  println(s"Sum of powers of minumum draws: ${minimumDraws
      .map(minimumDraw => minimumDraw.numRed * minimumDraw.numGreen * minimumDraw.numBlue)
      .sum}")
}
