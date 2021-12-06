fun main() {

    val input = readInput("Day06")

    // **************** Brute force **************** //
    val lanternFish = input.firstOrNull()?.split(",")?.mapNotNull { it.toLongOrNull() } ?: listOf()

    fun generateGeneration(curr: List<Long>, newFish: MutableList<Long>) = curr.map {
        if (it == 0.toLong()) {
            newFish.add(8)
            6
        } else {
            it - 1
        }
    }.toList()
    
    fun List<Long>.calculateFish(size: Int): List<Long> {
        val fish = this
        return (0 until size).fold(fish to fish) { (_, curr), _ ->
                val newFish = mutableListOf<Long>()
                val currentFish = generateGeneration(curr, newFish)
                curr to listOf(newFish, currentFish).flatten()
            }.second
    }
    // ************************ //


    // **************** Not brute force, did the job *************** //
    fun runGenerations(start: List<Long>, generations: Int): List<Long> {
        // Deque representing (index, value) : (time/day, no of fish creating nextgen that day)
        val curr = ArrayDeque(start)

        //repeat for generations
        repeat (generations) {
            // simulate a day advancing in time by removing the first
            val spawn = curr.removeFirst()
            // don't care about which fishes are in day08 slots and all just add spawned fish
            curr.add(spawn)
            curr[6] += spawn
        }
        return curr.toList()
    }
    // ***************************** //

    fun part1(): Int {
        return lanternFish.calculateFish(80).count()
    }

    fun part2(): Long {
        val start = MutableList(9) { 0L }
        input.first().split(",").forEach { start[it.toInt()]++ }
        return runGenerations(start, 256).sum()
    }

//    println(part1())
    println(part2())
}