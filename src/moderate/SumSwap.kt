package moderate

import extensions.print
import java.lang.Exception

/**
 * Sum Swap: Given two arrays of integers, find a pair of values (one value from each array) that you
 * can swap to give the two arrays the same sum.
 * EXAMPLE
 * Input: (4, 1, 2, 1, 1, 2) and (3, 6, 3, 3)
 * Output: (1, 3)
 **/
class SumSwap {

    fun getPair(a: IntArray, b: IntArray): Pair<Int, Int>? {
        val sumA = a.sum()
        val sumB = b.sum()
        val diff = (sumA - sumB)
        if (diff % 2 != 0) throw Exception("No equal sum possible")
        val reqDiff = diff / 2

        a.forEach { i ->
            b.forEach { j ->
                if (i - j == reqDiff) {
                    return Pair(i, j)
                }
            }
        }
        return null
    }

    fun getPairOptimized(a: IntArray, b: IntArray): Pair<Int, Int>? {
        val map = HashSet<Int>()
        val sumA = a.sum()
        var sumB = 0
        b.forEach {
            sumB += it
            map.add(it)
        }
        val diff = (sumA - sumB)
        if (diff % 2 != 0) throw Exception("No equal sum possible")
        val reqDiff = diff / 2

        a.forEach { i ->
            val j = i - reqDiff
            if (map.contains(j)) {
                return Pair(i, j)
            }
        }
        return null
    }

}

fun main() {
    SumSwap().apply {
        getPair(
                intArrayOf(4, 1, 2, 1, 1, 2), intArrayOf(3, 6, 3, 3)
        ).print()
        getPairOptimized(
                intArrayOf(4, 1, 2, 1, 1, 2), intArrayOf(3, 6, 3, 3)
        ).print()
    }
}