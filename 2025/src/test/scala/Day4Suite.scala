class Day4Suite extends munit.FunSuite {
  val inputGrid = """..@@.@@@@.
@@@.@.@.@@
@@@@@.@.@@
@.@@@@..@.
@@.@@@@.@@
.@@@@@@@.@
.@.@.@.@@@
@.@@@.@@@@
.@@@@@@@@.
@.@.@@@.@.""".linesIterator.toList.map(_.toList)

  test("findNumAdjacentRolls") {
    val outputGrid = updateGridWithRollsToRemove(inputGrid)
      .map(_.mkString)
      .mkString("\n")

    val expectedOutputGrid = """..xx.xx@x.
x@@.@.@.@@
@@@@@.x.@@
@.@@@@..@.
x@.@@@@.@x
.@@@@@@@.@
.@.@.@.@@@
x.@@@.@@@@
.@@@@@@@@.
x.x.@@@.x."""

    assertEquals(outputGrid, expectedOutputGrid)
  }

  test("numRollsThatCanBeRemoved") {
    assertEquals(numRollsThatCanBeRemoved(inputGrid), 43)
  }
}
