package sorting_searching

import extensions.print

/**
 * Search in Rotated Array: Given a sorted array of n integers that has been rotated an unknown
 * number of times, write code to find an element in the array. You may assume that the array was
 * originally sorted in increasing order.
 * EXAMPLE
 * Input: find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}
 * Output: 8 (the index of 5 in the array)
 **/
class SearchRotatedArray {

    fun binarySearchFind(array: IntArray, value: Int): Int {
        return binarySearchFind(array, value, 0, array.size - 1)
    }

    private fun binarySearchFind(a: IntArray, value: Int, start: Int, end: Int): Int {

        val mid = (start + end) / 2
        if (a[mid] == value) {
            return mid
        }

        if (a[start] < a[mid]) { //Left normally ordered

            //Check if the value stays in the normally ordered range
            return if (value >= a[start] && value < a[mid]) {
                binarySearchFind(a, value, start, mid - 1) //search left
            } else {
                binarySearchFind(a, value, mid + 1, end) //search right
            }

        } else if (a[mid] < a[end]) { //Right normally ordered

            //Check if the value stays in the normally ordered range
            return if (value > a[mid] && value <= a[end]) {
                binarySearchFind(a, value, mid + 1, end)//search right
            } else {
                binarySearchFind(a, value, start, mid - 1)//search left
            }

        } else if (a[start] == a[mid]) {

            return if (a[mid] != a[end]) {
                binarySearchFind(a, value, mid + 1, end)//search right
            } else { // Search whole array ex: 2 2 2 2 4 -> rotated -> 2 4 2 2 2
                val result = binarySearchFind(a, value, start, mid - 1)
                if (result == -1) {
                    binarySearchFind(a, value, mid + 1, end)
                } else {
                    result
                }
            }

        }
        return -1

    }

    fun find(array: IntArray, value: Int): Int {

        var canGoBack = true
        var i = 0
        while (i >= 0 && i < array.size) {

            if (array[i] == value) break

            if (array[i] > value) {
                i--
                if (i == -1 && canGoBack) {
                    canGoBack = false
                    i = array.size - 1
                }
            } else {
                i++
            }
        }
        return i
    }

}

fun main() {
    SearchRotatedArray().apply {
        find(
                intArrayOf(15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14), 5
        ).print()
        binarySearchFind(
                intArrayOf(15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14), 5
        ).print()
        binarySearchFind(
                intArrayOf(1, 2, 3, 4, 5), 1
        ).print()

        binarySearchFind(
                intArrayOf(5, 1, 2, 3, 4), 1
        ).print()
        binarySearchFind(
                intArrayOf(2, 4, 2, 2, 2), 4
        ).print()

    }
}