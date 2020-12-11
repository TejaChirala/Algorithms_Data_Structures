package sorting_searching

import extensions.print

class MergeSort {

    fun sort(array: IntArray): IntArray {
        return sort(array, IntArray(array.size), 0, array.size - 1)
    }

    private fun sort(array: IntArray, temp: IntArray, start: Int, end: Int): IntArray {
        if (start < end) {
            val mid = (start + end) / 2
            //Sort Left
            sort(array, temp, start, mid)
            //Sort Right
            sort(array, temp, mid + 1, end)
            //Merge
            merge(array, temp, start, end)
        }
        return array
    }

    private fun merge(array: IntArray, temp: IntArray, start: Int, end: Int) {
        val mid = (start + end) / 2
        var leftStart = start
        val leftEnd = mid
        var rightStart = mid + 1
        val rightEnd = end

        for (i in start..end) {
            temp[i] = array[i]
        }
        var index = start
        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (temp[leftStart] <= temp[rightStart]) {
                array[index] = temp[leftStart]
                leftStart++
            } else if (temp[leftStart] > temp[rightStart]) {
                array[index] = temp[rightStart]
                rightStart++
            }
            index++
        }
        for (i in leftStart..leftEnd) {
            array[index] = temp[i]
            index++
        }
    }
}

fun main() {
    MergeSort().apply {
        sort(intArrayOf(4, 3, 2, 1)).print()
        sort(intArrayOf(4, 3, 2, 2, 5)).print()
    }
}