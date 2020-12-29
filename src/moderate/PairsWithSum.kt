package moderate

import extensions.print

/**
 * Pairs with Sum: Design an algorithm to find all pairs of integers within an array which sum to a
 * specified value.
 **/
class PairsWithSum {

    fun findPairs(a: IntArray, sum: Int): HashSet<Pair<Int, Int>> {
        val map = HashMap<Int, Int>()
        val result = HashSet<Pair<Int, Int>>()
        a.forEach { i ->
            val diff = sum - i
            if (map.getOrDefault(diff, 0) > 0) {
                result.add(Pair(i, diff))
                map[diff] = map[diff]!! - 1
            } else {
                map[i] = 1
            }
        }
        return result
    }

}

fun main() {

    PairsWithSum().apply {
        findPairs(intArrayOf(5, 5, 5, 5), 10).print()
        findPairs(intArrayOf(6, 7, 5, 2, 0), 7).print()
    }

}