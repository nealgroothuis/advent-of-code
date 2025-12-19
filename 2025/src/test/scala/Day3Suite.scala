class Day3Suite extends munit.FunSuite {
  test("highestJoltage") {
    val input1 = "987654321111111".map(_.asDigit).toList
    assertEquals(HighestJoltage.joltage(highestJoltage(input1)), 98)
    val input2 = "811111111111119".map(_.asDigit).toList
    assertEquals(HighestJoltage.joltage(highestJoltage(input2)), 89)
    val input3 = "234234234234278".map(_.asDigit).toList
    assertEquals(HighestJoltage.joltage(highestJoltage(input3)), 78)
    val input4 = "818181911112111".map(_.asDigit).toList
    assertEquals(HighestJoltage.joltage(highestJoltage(input4)), 92)
  }

  test("highestJoltageDay2") {
    assertEquals(
      highestJoltageDay2(List(9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1)),
      List(9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1)
    )
    assertEquals(
      highestJoltageDay2(List(8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9)),
      List(8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9)
    )
    assertEquals(
      highestJoltageDay2(List(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8)),
      List(4, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8)
    )
    assertEquals(
      highestJoltageDay2(List(8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1)),
      List(8, 8, 8, 9, 1, 1, 1, 1, 2, 1, 1, 1)
    )
  }
}
