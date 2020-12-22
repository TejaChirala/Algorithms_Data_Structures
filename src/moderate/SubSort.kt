package moderate

import extensions.print
import kotlin.math.min
import kotlin.math.max

/**
 * Sub Sort: Given an array of integers, write a method to find indices m and n such that if you sorted
 * elements m through n, the entire array would be sorted. Minimize n - m (that is, find the smallest
 * such sequence).
 * EXAMPLE
 * Input: 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19
 * Output: (3, 9)
 **/
class SubSort {

    fun getIndexes(array: Array<Int>): Pair<Int, Int> {

        if (array.isEmpty()) return Pair(0, 0)

        var leftEnd = 0
        for (i in array.indices) {
            if (array[leftEnd] <= array[i]) {
                leftEnd = i
            } else {
                break
            }
        }
        if (leftEnd == array.size - 1) return Pair(0, 0)

        var rightStart = array.size - 1
        for (i in array.size - 1 downTo 0) {
            if (array[rightStart] >= array[i]) {
                rightStart = i
            } else {
                break
            }
        }

        val minMaxPair = getMinMax(leftEnd + 1, rightStart - 1, array)
        val min = min(minMaxPair.first, array[rightStart])
        val max = max(minMaxPair.second, array[leftEnd])

        var subSortStartIndex = 0
        for (i in 0..leftEnd) {
            if (array[i] > min) {
                subSortStartIndex = i
                break
            }
        }

        var subSortEndIndex = 0
        for (i in rightStart until array.size) {
            if (array[i] <= max) {
                subSortEndIndex = i
            } else {
                break
            }
        }
        return Pair(subSortStartIndex, subSortEndIndex)
    }

    private fun getMinMax(start: Int, end: Int, array: Array<Int>): Pair<Int, Int> {

        var min = array[start]
        var max = array[start]
        for (i in start..end) {
            min = min(min, array[i])
            max = max(max, array[i])
        }
        return Pair(min, max)
    }

}

fun main() {
    SubSort().apply {
        getIndexes(
                arrayOf(1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19)
        ).print()

        getIndexes(
                arrayOf(1, 2, 4, 7, 10, 11)
        ).print()
    }
}