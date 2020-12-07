package dynamic_programming

import extensions.print
import kotlin.math.max
import kotlin.math.min

/**
 * Magic Index: A magic index in an array A[0 ... n -1] is defined to be an index such that A[i] =i.
 * Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in
 * array A.
 * FOLLOW UP
 * What if the values are not distinct?
 **/
class MagicIndex {

    /**
     * Looping through the entire array with O(n)
     **/
    fun getMagicIndex(a: Array<Int>): Int {
        a.forEachIndexed { index, value ->
            if (index == value) return value
            if (value > a.size - 1) return -1
        }
        return -1
    }

    /**
     * Algorithm with O(log n)
     **/
    fun getMagicIndexOptimized(a: Array<Int>): Int {
        return getMagicIndexOptimized(a, 0, a.size - 1)
    }

    private fun getMagicIndexOptimized(a: Array<Int>, start: Int, end: Int): Int {
        if (start > end) return -1
        val middle = (start + end) / 2
        return when {
            a[middle] == middle -> {
                middle
            }
            a[middle] < middle -> {
                getMagicIndexOptimized(a, middle + 1, end)
            }
            else -> {
                getMagicIndexOptimized(a, start, middle - 1)
            }
        }
    }

    /**
     * FOLLOW UP with duplicate items in the array
     **/
    fun getMagicIndexDuplicates(a: Array<Int>): Int {
        return getMagicIndexDuplicates(a, 0, a.size - 1)
    }

    private fun getMagicIndexDuplicates(a: Array<Int>, start: Int, end: Int): Int {
        if (start > end) return -1
        val middle = (start + end) / 2
        if (a[middle] == middle) {
            return middle
        }

        if (a[middle] < middle) {
            val minIndex = min(middle - 1, a[middle])
            val result = getMagicIndexDuplicates(a, start, minIndex)
            if (result > -1) {
                return result
            }
        }

        val maxIndex = max(middle + 1, a[middle])
        return getMagicIndexDuplicates(a, maxIndex, end)
    }


}

fun main() {

    MagicIndex().apply {
        getMagicIndex(arrayOf(-3, -2, 0, 2, 3, 4, 6)).print()
        getMagicIndexOptimized(arrayOf(-3, -2, 0, 2, 3, 4, 6)).print()
        getMagicIndexDuplicates(arrayOf(-3, -2, 0, 2, 3, 4, 4, 7)).print()
    }

}