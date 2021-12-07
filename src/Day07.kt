import kotlin.math.abs
import kotlin.math.min

fun main() {

    val input = readInput("Day07").first().split(",").map { it.toInt() }

    fun getMinFuelCost(input: List<Int>, costFn: (p1: Int, p2: Int) -> Int): Int {
        var minCost = Int.MAX_VALUE
        for (i in input.indices) {
            var alignmentCost = 0
            for (j in input.indices) {
                if (i != j) {
                    alignmentCost += costFn(input[i], input[j])
                }
            }
            minCost = min(alignmentCost, minCost)
        }
        return minCost
    }

    fun part1(input: List<Int>): Int {
        return getMinFuelCost(input) { p1, p2 -> abs(p1 - p2) }
    }

    fun part2(input: List<Int>): Int {
        return getMinFuelCost(input) { p1, p2 -> abs(p1 - p2) * (abs(p1 - p2) + 1) / 2 }
    }

//    println(part1(input))
    println(part2(input))
}