package hard

import extensions.print

/**
 * Circus Tower: A circus is designing a tower routine consisting of people standing atop one another's
 * shoulders. For practical and aesthetic reasons, each person must be both shorter and lighter
 * than the person below him or her. Given the heights and weights of each person in the circus, write
 * a method to compute the largest possible number of people in such a tower.
 * EXAMPLE
 * Input (ht, wt): (65 , 100) (70, 150) (56 , 90) (75 , 190) (60, 95) (68 , 110)
 * Output: The longest tower is length 6 and includes from top to bottom:
 * (56 , 90) (60, 95) (65, 100) (68, 110) (70, 150) (75, 190)
 **/
class CircusTower {

    fun findMaxCount(m: List<Pair<Int, Int>>): Int {
        return longestIncreasingSubSequence(m.sortedBy { it.first })
    }

    private fun longestIncreasingSubSequence(m: List<Pair<Int, Int>>): Int {
        val dp = Array(m.size) { Int.MIN_VALUE }
        var length = 0
        for (p in m) {
            val h = p.second
            val i  = binarySearch(dp, 0, length - 1, h)
            dp[i] = h
            if (length == i) {
                length++
            }
        }
        return length
    }

    private fun binarySearch(list: Array<Int>, start: Int, end: Int, x: Int): Int {
        var s = start
        var e = end
        while (s <= e) {
            val m = (s+e)/2
            val middleItem = list[m]
            if (middleItem == x) {
                return m
            } else if (middleItem > x) {
                e = m - 1
            } else {
                s = m + 1
            }
        }
        return s
    }

}

fun main() {
    CircusTower().apply {
        findMaxCount(
                listOf(
                        Pair(65, 100),
                        Pair(70, 150),
                        Pair(56, 90),
                        Pair(75, 190),
                        Pair(60, 95),
                        Pair(68, 110)
                )
        ).print()
    }
}