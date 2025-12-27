class Day5Suite extends munit.FunSuite {

  test("parseFreshIDRanges") {
    assertEquals(
      parseFreshIDRanges(List("1-2", "3-4")),
      List(FreshIDRange(1, 2), FreshIDRange(3, 4))
    )
  }

  test("isInAnyRange") {
    val rangeStrings = """3-5
10-14
16-20
12-18""".linesWithSeparators.map(_.stripLineEnd).toList

    val availableIngredientIDs = """1
5
8
11
17
32""".linesWithSeparators.map(_.stripLineEnd).map(_.toLong).toList

    val ranges = parseFreshIDRanges(rangeStrings)
    assertEquals(availableIngredientIDs.count(isInAnyRange(ranges)), 3)
  }
}
