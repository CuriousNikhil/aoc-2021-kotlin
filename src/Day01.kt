fun main() {
    fun part1(input: List<String>): Int {
        return input.windowed(2)
            .count { (firstScan, secondScan) -> secondScan.toInt() > firstScan.toInt() }
    }

    fun part2(input: List<String>): Int {
        return input.windowed(3) { window -> window.sumOf { it.toInt() } }
            .windowed(2)
            .count { (firstScan, secondScan) -> secondScan > firstScan }

    }

    val input = readInput("Day01")

//    println(part1(input))
    println(part2(input))
}
