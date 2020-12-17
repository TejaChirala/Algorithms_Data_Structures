package moderate

import extensions.print
import kotlin.math.min

/**
 * Smallest Difference: Given two arrays of integers, compute the pair of values (one value in each
 * array) with the smallest (non-negative) difference. Return the difference.
 * EXAMPLE
 * Input: {1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
 * Output: 3. That is, the pair (11 , 8).
 **/
class SmallestDifference {

    //O(ab)
    fun getSmallestDiffBruteForce(a: Array<Int>, b: Array<Int>): Int {
        var smallestDifference = Int.MAX_VALUE
        a.forEach { i ->
            b.forEach { b ->
                val diff = i - b
                if (diff >= 0)
                    smallestDifference = min(smallestDifference, i - b)
            }
        }
        return smallestDifference
    }

    //O(a log a + b log b) + O(a + b) => total O(a log a + b log b)
    fun getSmallestDiffOptimized(a: Array<Int>, b: Array<Int>): Int {
        var smallestDifference = Int.MAX_VALUE

        a.sort()
        b.sort()

        var i = 0
        var j = 0
        while (i < a.size && j < b.size) {

            when {
                a[i] < b[j] -> {
                    i++
                }
                a[i] > b[j] -> {
                    smallestDifference = min(smallestDifference, a[i] - b[j])
                    j++
                }
                else -> {
                    smallestDifference = 0
                    i++
                    j++
                }
            }
        }

        return smallestDifference
    }

}

fun main() {
    SmallestDifference().apply {
        getSmallestDiffBruteForce(
                arrayOf(1, 3, 15, 11, 2),
                arrayOf(23, 127, 235, 19, 8)
        ).print()
        getSmallestDiffOptimized(
                arrayOf(1, 3, 15, 11, 2),
                arrayOf(23, 127, 235, 19, 8)
        ).print()
    }
}