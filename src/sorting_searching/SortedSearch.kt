package sorting_searching

import extensions.print
import java.util.*
import kotlin.collections.ArrayList

/**
 * Sorted Search, No Size: You are given an array-like data structure Listy which lacks a size
 * method. It does, however, have an elementAt(i) method that returns the element at index i in
 * 0(1) time. If i is beyond the bounds of the data structure, it returns - 1. (For this reason, the data
 * structure only supports positive integers.) Given a Listy which contains sorted, positive integers,
 * find the index at which an element x occurs. If x occurs multiple times, you may return any index.
 **/
class SortedSearch {

    fun find(listy: Listy, value: Int): Int {

        var index = 1
        while (listy.getElementAt(index) != -1 && listy.getElementAt(index) < value) {
            index *= 2
        }
        ArrayList<IntArray>().toTypedArray()
        LinkedList<Int>().toArray()
        val a = ArrayList<Int>().toArray()
        return binarySearch(listy, value, index / 2, index)

    }

    private fun binarySearch(listy: Listy, value: Int, s: Int, e: Int): Int {
        var start = s
        var end = e
        while (start <= end) {
            val mid = (start + end) / 2
            val midElement = listy.getElementAt(mid)
            if (midElement == -1 || midElement > value) {
                end = mid - 1
            } else if (midElement < value) {
                start = mid + 1
            } else {
                return mid
            }
        }
        return -1
    }


    class Listy(private val array: IntArray) {

        fun getElementAt(index: Int) = if (index >= array.size) -1 else array[index]

    }

}

fun main() {

    SortedSearch().apply {

        find(
                SortedSearch.Listy(
                        intArrayOf(1, 2, 3)
                ), 3
        ).print()

        find(
                SortedSearch.Listy(
                        intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                ), 10
        ).print()

        find(
                SortedSearch.Listy(
                        intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                ), 50
        ).print()

    }

}