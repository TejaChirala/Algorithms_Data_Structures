package sorting_searching

import extensions.print

/**
 * Sorted Merge: You are given two sorted arrays, A and B, where A has a large enough buffer at the
 * end to hold B. Write a method to merge B into A in sorted order.
 **/
class SortedMerge {

    fun merge(a: IntArray, b: IntArray): IntArray {

        var bEnd = b.size - 1
        var aEnd = a.size - b.size - 1
        var index = a.size - 1

        while (index >= 0 && aEnd >= 0 && bEnd >= 0) {

            if (a[aEnd] >= b[bEnd]) {
                a[index] = a[aEnd]
                aEnd--
            } else if (a[aEnd] < b[bEnd]) {
                a[index] = b[bEnd]
                bEnd--
            }
            index--

        }
        return a
    }

}

fun main() {
    SortedMerge().apply {
        merge(
                intArrayOf(1, 3, 5, 0, 0, 0),
                intArrayOf(2, 4, 6)
        ).print()

        merge(
                intArrayOf(1, 9, 15, 0, 0, 0),
                intArrayOf(2, 4, 62)
        ).print()
    }
}