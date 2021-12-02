fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var horizontal = 0
        input.forEach {
            val (steer, amt) = it.split(" ")
            val x = amt.toInt()
            when (steer) {
                "forward" -> horizontal += x
                "down" -> depth += x
                "up" -> depth -= x
            }
        }
        return depth * horizontal
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var horizontal = 0
        var aim = 0
        input.forEach {
            val(steer, amt) = it.split(" ")
            val x = amt.toInt()
            when (steer) {
                "forward" -> {
                    horizontal += x
                    depth += aim * x
                }
                "down" -> aim += x
                "up" -> aim -= x
            }
        }
        return depth * horizontal
    }

    val input = readInput("Day02")

//    println(part1(input))
    println(part2(input))
}
