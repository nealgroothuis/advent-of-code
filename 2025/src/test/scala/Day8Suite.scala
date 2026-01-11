class Day8Suite extends munit.FunSuite {
  val coordinates = """162,817,812
57,618,57
906,360,560
592,479,940
352,342,300
466,668,158
542,29,236
431,825,988
739,650,466
52,470,668
216,146,977
819,987,18
117,168,530
805,96,715
346,949,466
970,615,88
941,993,340
862,61,35
984,92,344
425,690,689""".split("\n").toList

  test("findSortedConnections") {
    val sortedConnections =
      findSortedConnections(coordinates.map(parseInputLine).toArray)
    assertEquals(sortedConnections.head.i, 0)
    assertEquals(sortedConnections.head.j, 19)
  }

  test("findCircuits") {
    val circuits = findCircuits(coordinates, 10)
    val sortedCircuits = circuits.toList.sortBy(-_.size)
    assertEquals(sortedCircuits.take(3).foldLeft(1)(_ * _.size), 40)
  }
}
