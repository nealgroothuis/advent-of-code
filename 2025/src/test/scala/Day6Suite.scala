class Day6Suite extends munit.FunSuite {
  test("transpose") {
    assertEquals(
      transpose(List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))),
      List(List(1, 4, 7), List(2, 5, 8), List(3, 6, 9))
    )
  }

  test("answerProblems") {
    assertEquals(
      answerProblems(
        List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9)),
        List("+", "*", "+")
      ),
      List(6, 120, 24)
    )
  }
}
