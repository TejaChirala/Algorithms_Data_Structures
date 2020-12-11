package sorting_searching

import extensions.print

class QuickSort {

    fun sort(array: IntArray): IntArray {
        return sort(array, 0, array.size - 1)
    }

    fun sort(array: IntArray, start: Int, end: Int): IntArray {

        val pivot = getProperPivot(array, start, end)

        if (start < pivot - 1) {
            sort(array, start, pivot - 1)
        }

        if (pivot < end) {
            sort(array, pivot, end)
        }
        return array
    }

    private fun getProperPivot(array: IntArray, start: Int, end: Int): Int {
        val pivot = (start + end) / 2
        var i = start
        var j = end

        while (i <= j) {

            while (array[i] < array[pivot]) i++
            while (array[j] > array[pivot]) j--

            if (i <= j) {
                val temp = array[i]
                array[i] = array[j]
                array[j] = temp
                i++
                j--
            }

        }
        return i
    }

}

fun main() {
    QuickSort().apply {
        sort(intArrayOf(4, 3, 2, 1)).print()
        sort(intArrayOf(4, 3, 2, 2, 5)).print()
    }
}