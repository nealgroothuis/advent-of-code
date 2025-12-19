class Day2Suite extends munit.FunSuite {
  test("parseProductIdRanges") {
    assertEquals(
      parseProductIdRanges(
        "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"
      ),
      List(
        ProductIdRange(11, 22),
        ProductIdRange(95, 115),
        ProductIdRange(998, 1012),
        ProductIdRange(1188511880, 1188511890),
        ProductIdRange(222220, 222224),
        ProductIdRange(1698522, 1698528),
        ProductIdRange(446443, 446449),
        ProductIdRange(38593856, 38593862),
        ProductIdRange(565653, 565659),
        ProductIdRange(824824821, 824824827),
        ProductIdRange(2121212118, 2121212124)
      )
    )
  }

  test("decimalDigits") {
    assertEquals(decimalDigits(1234), List(1, 2, 3, 4))
  }

  test("isInvalidId") {
    assertEquals(isInvalidId(55), true)
    assertEquals(isInvalidId(6464), true)
    assertEquals(isInvalidId(123123), true)
    assertEquals(isInvalidId(101), false)
  }

  test("isInvalidIdPart2") {
    assertEquals(isInvalidIdPart2(55), true)
    assertEquals(isInvalidIdPart2(6464), true)
    assertEquals(isInvalidIdPart2(123123), true)
    assertEquals(isInvalidIdPart2(101), false)
    assertEquals(isInvalidIdPart2(101101101), true)
  }

  test("ProductIdRange.invalidIds") {
    assertEquals(ProductIdRange.invalidIds(ProductIdRange(11, 13)), List(11L))
  }
}
