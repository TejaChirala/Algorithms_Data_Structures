package moderate

import extensions.print

class LivingPeople {

    fun findGoodYear(ranges: List<Pair<Int, Int>>): Int {
        val decade = IntArray(101)
        ranges.forEach {
            decade[it.first] = decade[it.first] + 1
            decade[it.second] = decade[it.second] - 1
        }
        var maxSum = 0
        var finalIndex = 0
        var sum = 0
        decade.forEachIndexed { index, i ->
            sum += i
            if (sum > maxSum) {
                maxSum = sum
                finalIndex = index
            }
        }
        return finalIndex + 1900
    }

}

fun main() {
    LivingPeople().apply {
        findGoodYear(
                listOf(
                        Pair(12, 15),
                        Pair(20, 90),
                        Pair(10, 98),
                        Pair(1, 72),
                        Pair(10, 98),
                        Pair(23, 82),
                        Pair(13, 98),
                        Pair(90, 98),
                        Pair(83, 99),
                        Pair(75, 94),
                )
        ).print()
    }
}