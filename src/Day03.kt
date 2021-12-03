fun part1(input: List<String>): Int {
    val gamma = StringBuilder()

    getVerticalFrequencies(input).forEach { frequency ->
        val msb = frequency.maxByOrNull { it.value }?.key
        gamma.append(msb.toString())
    }
    val epsilon = gamma.map { if (it == '0') '1' else '0' }.joinToString("")
    return gamma.toString().toInt(2) * epsilon.toInt(2)
}

fun part2(input: List<String>): Int {
    val co2 = filterLifeSupportRating(input) { a, b -> if (a > b) '1' else '0' }
    val oxygen = filterLifeSupportRating(input) { a, b -> if (a > b) '0' else '1' }
    return oxygen.toInt(2) * co2.toInt(2)
}

fun filterLifeSupportRating(input: List<String>, requiredCharFreq: (zeroes: Int, ones: Int) -> Char): String {
    var list = input
    for (i in list[0].indices) {
        val charFrequency = list.groupingBy { it[i] }.eachCount()
        val one = charFrequency['1'] ?: 0
        val zero = charFrequency['0'] ?: 0
        list = list.filter { it[i] == requiredCharFreq(zero, one) }
        if (list.size == 1) break
    }
    return list.single()
}


fun getVerticalFrequencies(input: List<String>) = input[0].indices.map { index ->
    input.groupingBy { it[index] }.eachCount()
}

fun main() {

    val input = readInput("Day03")

//    println(part1(input))
    println(part2(input))
}