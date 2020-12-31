package hard

import extensions.print
import kotlin.math.max

/**
 * Letters and Numbers: Given an array filled with letters and numbers, find the longest subarray with
 * an equal number of letters and numbers.
 **/
class LettersAndNumbers {

    fun longestEqualSubArray(a: CharArray): Int {
        var chars = 0
        var digits = 0
        var result = 0
        val map = HashMap<Int, Int>()
        map[0] = -1

        a.forEachIndexed { index, c ->
            if (Character.isDigit(c)) {
                digits++
            } else {
                chars++
            }
            val diff = chars - digits
            if (map.containsKey(diff)) {
                result = max(index - map[diff]!!, result)
            } else {
                map[diff] = index
            }
        }
        return result
    }

}

fun main() {
    LettersAndNumbers().apply {
        longestEqualSubArray(
                charArrayOf('a', 'a', 'a', '1', '1', '1')
        ).print()
        longestEqualSubArray(
                charArrayOf('a', '1', 'a', 'a', 'a', '1', '1')
        ).print()
        longestEqualSubArray(
                charArrayOf('a', 'a', 'a', 'a', '1', '1', 'a', '1', '1', 'a', 'a', '1', 'a', 'a', '1', 'a', 'a', 'a', 'a', 'a')
        ).print()
    }
}