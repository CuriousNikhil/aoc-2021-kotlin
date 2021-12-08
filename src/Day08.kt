// considering SSD with numbers i.e using numbers to represent 0 to 9 digits
val segmentToDigitMap = mapOf(
    setOf(0, 1, 2, 4, 5, 6) to 0,
    setOf(2, 5) to 1,
    setOf(0, 2, 3, 4, 6) to 2,
    setOf(0, 2, 3, 5, 6) to 3,
    setOf(1, 2, 3, 5) to 4,
    setOf(0, 1, 3, 5, 6) to 5,
    setOf(0, 1, 3, 4, 5, 6) to 6,
    setOf(0, 2, 5) to 7,
    setOf(0, 1, 2, 3, 4, 5, 6) to 8,
    setOf(0, 1, 2, 3, 5, 6) to 9
)


fun main() {
    val input = readInput("Day08")


    fun part1(): Int {
        val outputVal = input.map { it.split(" | ").last() }
        val digits = outputVal.flatMap { it.split(" ") }
        return digits.count { it.length in listOf(2, 3, 4, 7) }
    }

    fun permutateToGetMapping(patterns: List<String>): Map<Char, Int> {
        val chars = 'a'..'g'
        val wires = 0..6
        label@ while (true) {
            val collection = chars.zip(wires.shuffled()).toMap()
            for (pattern in patterns) {
                val mapped = pattern.map { collection[it]!! }.toSet()
                val isValidDigit = segmentToDigitMap.containsKey(mapped)
                if (!isValidDigit) continue@label
            }
            return collection
        }
    }

    fun calculateOutputs(pattern:List<String>, output:List<String>): Int{
        val patternMap = permutateToGetMapping(pattern)
        val sbuilder = StringBuilder()
        // the expected output here
        output.forEach {
            val seg = it.map { pattern -> patternMap[pattern]!! }.toSet()
            sbuilder.append(segmentToDigitMap[seg]!!)
        }
        return sbuilder.toString().toInt()
    }


    fun part2(): Int {
        val entry = input.map {
            val (signalPattern, output) = it.split(" | ")
            signalPattern.split(" ") to output.split(" ")
        }

        return entry.sumOf { (pattern, output) -> calculateOutputs(pattern, output) }
    }


//    println(part1())
    println(part2())
}