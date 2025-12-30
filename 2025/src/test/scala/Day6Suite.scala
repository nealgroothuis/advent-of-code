class Day6Suite extends munit.FunSuite {
  test("transpose") {
    assertEquals(
      transpose(List(List(1L, 2L, 3L), List(4L, 5L, 6L), List(7L, 8L, 9L))),
      List(List(1L, 4L, 7L), List(2L, 5L, 8L), List(3L, 6L, 9L))
    )
  }

  test("answerProblems") {
    assertEquals(
      answerProblems(
        List(List(1L, 2, 3), List(4, 5, 6), List(7, 8, 9)),
        List("+", "*", "+")
      ),
      List(6L, 120, 24)
    )
  }

  test("transpose Char") {
    val input =
      List("123 328  51 64 ", " 45 64  387 23 ", "  6 98  215 314").map(
        _.toCharArray.toList
      )

    assertEquals(
      transpose(input).map(_.mkString.strip),
      List(
        "1",
        "24",
        "356",
        "",
        "369",
        "248",
        "8",
        "",
        "32",
        "581",
        "175",
        "",
        "623",
        "431",
        "4"
      )
    )
  }

  test("answerPart2") {
    val input = List(
      "123 328  51 64 ",
      " 45 64  387 23 ",
      "  6 98  215 314",
      "*   +   *   +  ")

    assertEquals(answerProblemsPart2(input), List(8544L, 625L, 3253600L, 1058L).reverse)
  }
}
