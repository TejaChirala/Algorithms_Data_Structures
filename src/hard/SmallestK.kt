package hard

import extensions.print
import kotlin.random.Random

/**
 * Smallest K: Design an algorithm to find the smallest K numbers in an array.
 **/
class SmallestK {

    fun findSmallest(k: Int, a: IntArray): List<Int> {
        var rank = getRandom(0, a.size - 1, a)
        var start = 0
        var end = a.size - 1
        var index = -1
        while (index != k) {
            index = partition(a, start, end, rank)
            if (index < k) {
                rank = getRandom(index + 1, end, a)
                start = index + 1
            } else if (index > k) {
                rank = getRandom(start, index - 1, a)
                end = index - 1
            }
        }
        return a.slice(0 until k)

    }

    private fun getRandom(start: Int, end: Int, a: IntArray): Int {
        return Random.nextInt(start, end + 1)
    }

    private fun partition(a: IntArray, start: Int, end: Int, rank: Int): Int {

        swap(a, rank, end)
        val p = a[end]
        var i = start
        var j = start
        while (j < end) {
            if (a[j] <= p) {
                swap(a, i, j)
                i++
                j++
            } else {
                j++
            }
        }
        swap(a, i, end)
        return i

    }

    private fun swap(a: IntArray, i: Int, j: Int) {
        val temp = a[i]
        a[i] = a[j]
        a[j] = temp
    }

}

fun main() {
    SmallestK().apply {
        findSmallest(2, intArrayOf(5, 4, 3, 2, 1)).print()
        findSmallest(2, intArrayOf(1, 2, 3, 4, 5)).print()
        findSmallest(2, intArrayOf(1, 1, 2, 2, 3)).print()
        findSmallest(2, intArrayOf(3, 4, 3, 5, 5)).print()
        findSmallest(5, intArrayOf(3, 2, 1, 5, 6, 4)).print()
    }
}