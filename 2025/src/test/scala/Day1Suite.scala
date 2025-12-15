// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
class Day1Suite extends munit.FunSuite {

  test("clicks") {
    assertEquals(clicks(List("L42", "R3")), List(-42, 3))
  }

  test("positionsAndNumClicksEndingOnZero") {
    val clicks = List(-68, -30, 48, -5, 60, -55, -1, -99, 14, -82)
    val expectedPositionsAndNumClicksEndingOnZero = List(
      PositionAndNumClicksEndingOnZero(50, 0),
      PositionAndNumClicksEndingOnZero(82, 1),
      PositionAndNumClicksEndingOnZero(52, 0),
      PositionAndNumClicksEndingOnZero(0, 1),
      PositionAndNumClicksEndingOnZero(95, 0),
      PositionAndNumClicksEndingOnZero(55, 1),
      PositionAndNumClicksEndingOnZero(0, 1),
      PositionAndNumClicksEndingOnZero(99, 0),
      PositionAndNumClicksEndingOnZero(0, 1),
      PositionAndNumClicksEndingOnZero(14, 0),
      PositionAndNumClicksEndingOnZero(32, 1)
    )
    assertEquals(
      positionsAndNumClicksEndingOnZero(clicks),
      expectedPositionsAndNumClicksEndingOnZero
    )
  }
}
