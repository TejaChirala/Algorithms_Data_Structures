package sorting_searching

import extensions.print
import linked_lists.printLine

/**
 * Peaks and Valleys: In an array of integers, a "peak" is an element which is greater than or equal to
 * the adjacent integers and a "valley" is an element which is less than or equal to the adjacent integers.
 * For example, in the array {5, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and {S, 2} are valleys. Given an array
 * of integers, sort the array into an alternating sequence of peaks and valleys.
 * EXAMPLE
 * Input: {5, 3, 1, 2, 3}
 * Output: {5, 1, 3, 2, 3}
 **/
class PeaksAndValleys {

    fun arrangeSubOptimal(array: IntArray): IntArray {

        array.sort()
        for (i in 1 until array.size step 2) {
            swap(array, i, i - 1)
        }
        return array
    }

    fun arrangeOptimal(array: IntArray): IntArray {

        for (i in 1 until array.size step 2) {
            val smallestIndex = getSmallest(array, i - 1, i, i + 1)
            if (i != smallestIndex) {
                swap(array, i, smallestIndex)
            }
        }

        return array
    }

    private fun getSmallest(array: IntArray, a: Int, b: Int, c: Int): Int {

        val s = array[a]
        val m = array[b]
        val e = if (c < array.size) array[c] else Int.MAX_VALUE

        if (s <= m && s <= e) return a
        if (m <= s && m <= e) return b
        return c

    }

    private fun swap(array: IntArray, a: Int, b: Int) {
        val temp = array[a]
        array[a] = array[b]
        array[b] = temp
    }

}

fun main() {
    PeaksAndValleys().apply {
        arrangeSubOptimal(intArrayOf(5, 4, 3, 2, 1)).print()
        arrangeSubOptimal(intArrayOf(5)).print()
        arrangeSubOptimal(intArrayOf(5, 4)).print()
        arrangeSubOptimal(intArrayOf(5, 4, 3)).print()
        printLine()
        arrangeOptimal(intArrayOf(5, 4, 3, 2, 1)).print()
        arrangeOptimal(intArrayOf(5)).print()
        arrangeOptimal(intArrayOf(5, 4)).print()
        arrangeOptimal(intArrayOf(5, 4, 3)).print()
        arrangeOptimal(intArrayOf(5, 4, 3, 2)).print()
    }
}