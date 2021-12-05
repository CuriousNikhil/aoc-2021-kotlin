data class Points(val x: Int, val y: Int)
data class Seg(val from: Points, val to: Points)

fun main() {
    val input = readInput("Day05")

    fun delta(startX: Int, startY: Int, endX: Int, endY: Int): Points =
        Points(endX.compareTo(startX), endY.compareTo(startY))

    fun countVents(segments: List<Seg>): Int {
        val grid = (0 until 1000).map { (0 until 1000).map { 0 }.toMutableList() }
        for (seg in segments) {
            var (x1, y1) = seg.from
            var (x2, y2) = seg.to
            val d = delta(x1, y1, x2, y2)
            for (i in 0 until maxOf(kotlin.math.abs(x1 - x2), kotlin.math.abs(y1 - y2)) + 1) {
                grid[y1][x1]++
                x1 += d.x
                y1 += d.y
            }
        }
        return grid.sumOf { row -> row.count { it >= 2 } }
    }

    val segments = input.map {
        val (from, to) = it.split(" -> ")
        val (x1, y1) = from.split(",")
        val (x2, y2) = to.split(",")
        Seg(Points(x1.toInt(), y1.toInt()), Points(x2.toInt(), y2.toInt()))
    }

    fun part1(): Int {
        return countVents(segments.filter { it.from.x == it.to.x || it.from.y == it.to.y })
    }

    fun part2(): Int {
        return countVents(segments)
    }

//    println(part1())
    println(part2())
}