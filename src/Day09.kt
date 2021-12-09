fun main() {
    val input = readInput("Day09")

    fun part1(): Int {
        var riskLevelSum = 0
        // Don't use this use isLowPoint function written for part 2
        input.forEachIndexed { row, s ->
            s.forEachIndexed { col, c ->
                val height = c.digitToInt()
                var isLowest = true
                //left
                if (col > 0 && height >= s[col - 1].digitToInt()) isLowest = false
                //right
                if (col < s.length - 1 && height >= s[col + 1].digitToInt()) isLowest = false
                //up
                if (row > 0 && height >= input[row - 1][col].digitToInt()) isLowest = false
                //down
                if (row < input.size - 1 && height >= input[row + 1][col].digitToInt()) isLowest = false

                if (isLowest) {
                    riskLevelSum += (height + 1)
                }
            }
        }
        return riskLevelSum
    }

    fun isLowPoint(x: Int, y: Int, heightMap: List<List<Int>>): Boolean {
        var isLowest = true
        if (y > 0) isLowest = isLowest && heightMap[y][x] < heightMap[y - 1][x]
        if (y < heightMap.size - 1) isLowest = isLowest && heightMap[y][x] < heightMap[y + 1][x]
        if (x > 0) isLowest = isLowest && heightMap[y][x] < heightMap[y][x - 1]
        if (x < heightMap[0].size - 1) isLowest = isLowest && heightMap[y][x] < heightMap[y][x + 1]
        return isLowest
    }

    fun expandAroundPoint(
        x: Int,
        y: Int,
        heightMap: List<List<Int>>,
        visited: MutableSet<String> = mutableSetOf()
    ): Int {
        // dont include height 9
        if (x > heightMap[0].size - 1 || y > heightMap.size - 1 || y < 0 || x < 0 || heightMap[y][x] == 9) return 0
        // if already visited return
        if ("$y,$x" in visited) return 0

        // mark the height as visited
        visited.add("$y,$x")

        // explore around top, left, bottom, right
        if (x < heightMap[y].size) expandAroundPoint(x + 1, y, heightMap, visited)
        if (y < heightMap.size) expandAroundPoint(x, y + 1, heightMap, visited)
        if (x > 0) expandAroundPoint(x - 1, y, heightMap, visited)
        if (y > 0) expandAroundPoint(x, y - 1, heightMap, visited)

        // return basin size
        return visited.size
    }


    fun part2(): Int {
        val heightMap = input.map { it.split("").drop(1).dropLast(1).map { it.toInt() } }
        val sizes = mutableListOf<Int>()
        for (y in input.indices) {
            for (x in 0 until heightMap[y].size) {
                if (isLowPoint(x, y, heightMap)) {
                    sizes.add(expandAroundPoint(x, y, heightMap))
                }
            }
        }
        return sizes.sorted().takeLast(3).reduce { acc, i -> acc * i }
    }

//    println(part1())
    println(part2())
}