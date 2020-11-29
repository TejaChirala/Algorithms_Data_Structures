package bit_manipulation

import extensions.print
import kotlin.math.abs
import kotlin.math.max

/**
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 * Return the length of the longest (contiguous) subarray that contains only 1s.
 * EXAMPLE
 * Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * Output: 6
 **/
class FlipKBitsToWin {

    fun flipBitToWinArray(list: IntArray, k: Int): Int {
        var maxSequence = 0
        var i = 0
        var j = 0
        var count = k

        while (i < list.size) {
            if (count >= 0) {
                if (list[i] == 0) {
                    count--
                }
                if (count >= 0) {
                    maxSequence = max(maxSequence, abs(i - j + 1)) // 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0
                }
                i++
            } else {
                if (list[j] == 0) {
                    count++
                }
                j++
            }
        }
        return maxSequence
    }

    fun flipBitToWinArrayOptimized(list: IntArray, k: Int): Int {
        var i = 0
        var j = 0
        var count = k

        while (i < list.size) {
            if (list[i] == 0) {
                count--
            }
            if (count < 0) {
                if (list[j] == 0) {
                    count++
                }
                j++
            }
            i++
        }
        return i - j
    }

}

fun main() {
    FlipKBitsToWin().apply {
        flipBitToWinArray(intArrayOf(1, 0, 1, 0, 0), 1).print()
        flipBitToWinArray(intArrayOf(1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0), 2).print()
        flipBitToWinArray(intArrayOf(0, 0, 0, 1), 4).print()

        flipBitToWinArrayOptimized(intArrayOf(1, 0, 1, 0, 0), 1).print()
        flipBitToWinArrayOptimized(intArrayOf(1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0), 2).print()
        flipBitToWinArrayOptimized(intArrayOf(0, 0, 0, 1), 4).print()
    }
}