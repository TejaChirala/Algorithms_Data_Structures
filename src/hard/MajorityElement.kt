package hard

import extensions.print

/**
 * Majority Element: A majority element is an element that makes up more than half of the items in
 * an array. Given a positive integers array, find the majority element. If there is no majority element,
 * return -1. Do this in 0 (N) time and O( 1) space.
 * EXAMPLE
 * Input: 1 2 5 9 5 9 5 5 5
 * Output: 5
 **/
class MajorityElement {

    fun find(a: IntArray): Int {
        if (a.isEmpty()) return -1
        var count = 1
        var currentElement = a[0]
        for (i in 1 until a.size) {
            if (currentElement != a[i]) {
                count--
            } else {
                count++
            }

            if (count == 0) {
                currentElement = a[i]
                count = 1
            }
        }

        count = 0
        a.forEach {
            if (currentElement == it) {
                count++
            }
        }
        if (count > a.size / 2) {
            return currentElement
        }
        return -1
    }

}

fun main() {
    MajorityElement().apply {
        find(intArrayOf(1, 2, 5, 9, 5, 9, 5, 5, 5)).print()
        find(intArrayOf(1, 2)).print()
    }
}