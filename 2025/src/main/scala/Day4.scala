import scala.collection.mutable.ListBuffer
import scala.io.StdIn
import scala.annotation.tailrec

def findNumAdjacentRolls(rollRows: List[List[Char]]): Array[Array[Int]] = {
  val numRows = rollRows.length
  val numCols = rollRows(0).length
  val numAdjacentRolls = Array.ofDim[Int](numRows, numCols)
  rollRows.zipWithIndex.foreach((rollRow, rowIndex) =>
    rollRow.zipWithIndex.foreach((roll, colIndex) =>
      if (roll == '@') {
        val adjacentRowIndices = for {
          rowOffset <- List(-1, 0, 1)
          colOffset <- List(-1, 0, 1)
          if rowOffset != 0 || colOffset != 0
          newRowIndex = rowOffset + rowIndex
          newColIndex = colOffset + colIndex
          if newRowIndex >= 0 && newRowIndex < numRows && newColIndex >= 0 && newColIndex < numCols
        } yield (newRowIndex, newColIndex)
        adjacentRowIndices.foreach((x, y) =>
          numAdjacentRolls(x).update(y, numAdjacentRolls(x)(y) + 1)
        )
      }
    )
  )
  numAdjacentRolls
}

def updateGridWithRollsToRemove(
    rollRows: List[List[Char]]
): List[List[Char]] = {
  rollRows
    .zip(findNumAdjacentRolls(rollRows).toList.map(_.toList))
    .map(_.zip(_))
    .map(_.map {
      case ('.', _)                                        => '.'
      case ('@', numAdjacentRolls) if numAdjacentRolls < 4 => 'x'
      case ('@', _)                                        => '@'
      case _                                               => ???
    })
}

@tailrec
def numRollsThatCanBeRemoved(
    rollRows: List[List[Char]],
    numRollsRemoved: Int = 0
): Int = {
  val gridWithRollsToRemove = updateGridWithRollsToRemove(rollRows)
  val numRollsAccessibleByForklift =
    gridWithRollsToRemove.flatten.count(_ == 'x')
  if (numRollsAccessibleByForklift == 0) {
    numRollsRemoved
  } else {
    val gridWithRollsRemoved = gridWithRollsToRemove.map(_.map {
      case 'x'   => '.'
      case other => other
    })
    numRollsThatCanBeRemoved(
      gridWithRollsRemoved,
      numRollsRemoved + numRollsAccessibleByForklift
    )
  }
}

@main def day4main(): Unit = {
  val linesBuffer = ListBuffer.empty[String]
  var line = StdIn.readLine()
  while (line != null) {
    linesBuffer += line.strip
    line = StdIn.readLine()
  }
  val lines = linesBuffer.toList.map(_.toList)
  val outputGrid = lines
    .zip(findNumAdjacentRolls(lines).toList.map(_.toList))
    .map(_.zip(_))
    .map(_.map {
      case ('.', _)                                        => '.'
      case ('@', numAdjacentRolls) if numAdjacentRolls < 4 => 'x'
      case ('@', _)                                        => '@'
      case _                                               => ???
    })

  val numRollsAccessibleByForklift = outputGrid.flatten.count(_ == 'x')
  println(
    "Number of rolls accessible by forklift: " + numRollsAccessibleByForklift
  )

  val numRolls = numRollsThatCanBeRemoved(lines)
  println("Number of rolls that can be removed: " + numRolls)
}
