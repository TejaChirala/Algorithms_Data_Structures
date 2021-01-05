package hard

import extensions.print

/**
 * Count of 25: Write a method to count the number of 2s that appear in all the numbers between 0
 * and n (inclusive).
 * EXAMPLE
 * Input: 25
 * Output: 9 (2, 12,20, 21,22, 23,24 and 25. Note that 22 counts for two 2s.)
 **/
class CountOf2s {

    fun getCountOf2s(num: Int): Int {
        var n = num
        var i = 0
        var count = 0

        while (n > 0) {
            count += getCountAtDigit(num, n % 10, i)
            i++
            n /= 10
        }

        return count
    }

    private fun getCountAtDigit(n: Int, digit: Int, power: Int): Int {

        val powerOf10 = Math.pow(10.toDouble(), power.toDouble()).toInt()
        val nextPowerOf10 = powerOf10 * 10

        val runDown = n - (n % nextPowerOf10)
        val runUp = runDown + nextPowerOf10

        return when {

            digit < 2 -> {
                (runDown / 10)
            }
            digit > 2 -> {
                (runUp / 10)
            }
            else -> {
                val nextDigit = if (power == 0) 0 else (n % nextPowerOf10) / powerOf10
                (runDown / 10) + nextDigit + 1
            }

        }

    }

}

fun main() {
    CountOf2s().apply {
        getCountOf2s(1).print()
        getCountOf2s(2).print()
        getCountOf2s(12).print()
        getCountOf2s(13).print()
        getCountOf2s(100).print()
        getCountOf2s(200).print()
        getCountOf2s(300).print()
    }
}