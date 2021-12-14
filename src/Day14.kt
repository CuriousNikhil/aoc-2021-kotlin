fun main() {
    val input = readInput("Day14")

    val template = input.first()
    val rules = input.drop(2).map { it.split(" -> ") }.associate { (a, b) -> a to b }

    fun getNetQuantity(template:String, rules: Map<String, String>, steps: Int): Long {

        var state = template.windowed(2)
            .groupingBy { it }
            .eachCount()
            .mapValues { (_, value) -> value.toLong() }

        repeat(steps) {
            state = state.flatMap { (key, count) ->
                val char = rules.getValue(key)
                val s1 = key.first() + char
                val s2 = char + key.last()
                listOf(s1 to count, s2 to count)
            }.groupingBy { it.first }
                .fold(0L) { acum, (_, value) -> acum + value }
        }

        val singleCounter = mutableMapOf(template.last() to 1L)
        for ((key, value) in state) {
            singleCounter.compute(key.first()) { _, sum -> if (sum == null) value else sum + value }
        }
        val largest = singleCounter.maxOf { it.value }
        val smallest = singleCounter.minOf { it.value }
        return largest - smallest
    }


    fun part1(): Long {
        return getNetQuantity(template, rules, 10)
    }

    fun part2(): Long {
        return getNetQuantity(template, rules, 40)
    }


//    println(part1())
    println(part2())
}