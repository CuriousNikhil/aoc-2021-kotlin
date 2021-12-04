fun main() {
    val input = readInput("Day04")

    val numbers = input.first().split(",").map { it.toInt() }
    val boards = input.drop(1).filter { it.isNotEmpty() }.chunked(5).map {
        it.map { it.split(" ").filter { it.isNotEmpty() }.map { it.toInt() } }
    }

    fun part1(): Int {
        numbers.indices.forEach {
            val drawn = numbers.take(it + 1)
            boards.indexOfFirst { it.isWin(drawn) }.takeIf { it > -1 }?.let {
                val sum = boards[it].flatten().filter { it !in drawn }.sum()
                return sum * drawn.last()
            }
        }
        return -1
    }

    fun part2(): Int {
        val wins = mutableListOf<Int>()
        numbers.indices.forEach { upTo ->
            val drawn = numbers.take(upTo + 1)
            val newWins = boards.withIndex().filter { it.index !in wins && it.value.isWin(drawn) }.map { it.index }
            wins += newWins
            if (wins.size == boards.size) {
                val sum = boards[newWins.single()].flatten().filter { it !in drawn }.sum()
                return sum * drawn.last()
            }
        }
        return -1
    }

//    println(part1())
    println(part2())
}

fun List<List<Int>>.isWin(drawn: List<Int>): Boolean {
    val board = this
    val boardDrawn = board.any { it.all { it in drawn } }
    val checkNextDrawn = (0..4).map { column -> board.map { it[column] } }.any { it.all { it in drawn } }
    return boardDrawn || checkNextDrawn
}
