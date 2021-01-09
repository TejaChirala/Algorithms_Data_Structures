package hard

import extensions.print

/**
 * The Masseuse: A popular masseuse receives a sequence of back-to-back appointment requests
 * and is debating which ones to accept. She needs a 15-minute break between appointments and
 * therefore she cannot accept any adjacent requests. Given a sequence of back-to-back appointment
 * requests (all multiples of 15 minutes, none overlap, and none can be moved), find the optimal
 * (highest total booked minutes) set the masseuse can honor. Return the number of minutes.
 * EXAMPLE
 * Input: {30, 15, 60, 75, 45, 15, 15, 45}
 * Output: 180 minutes ({30, 60, 45, 45}).
 **/
class TheMasseuse {

    //Time: O(n), Space: O(n)
    fun find(a: IntArray): Int {
        val mem = IntArray(a.size + 2) { 0 }
        for (i in a.size - 1 downTo 0) {
            val with = a[i] + mem[i + 2]
            val withOut = mem[i + 1]
            mem[i] = maxOf(with, withOut)
        }
        return mem[0]
    }

    //Time: O(n), Space: O(1)
    fun findOptimized(a: IntArray): Int {
        var twoAway = 0
        var oneAway = 0
        for (i in a.size - 1 downTo 0) {
            val with = a[i] + twoAway
            val withOut = oneAway

            twoAway = oneAway
            oneAway = maxOf(with, withOut)
        }
        return oneAway
    }

}

fun main() {
    TheMasseuse().apply {
        find(intArrayOf(30, 15, 60, 75, 45, 15, 15, 45)).print()
        findOptimized(intArrayOf(30, 15, 60, 75, 45, 15, 15, 45)).print()
    }
}