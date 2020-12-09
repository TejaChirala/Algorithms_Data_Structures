package dynamic_programming

import extensions.print
import kotlin.math.min

/**
 * Coins: Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and
 * pennies (1 cent), write code to calculate the minimum number of coins required to make n cents.
 * If we cannot find a way we return -1
 * @link https://www.youtube.com/watch?v=jgiZlGzXMBw
 * */
class CoinsChangeMin {

    fun getWays(num: Int, d: Array<Int>): Int {
        val size = num + 1
        val array = Array(size) {
            if (it == 0) 0 else size
        }
        for (i in 1 until array.size) {
            d.forEach { denom ->
                if (denom <= i) {
                    val index = i - denom
                    array[i] = min(array[i], array[index] + 1)
                }
            }
        }
        val result = array[num]
        return if (result == size) -1 else result
    }

}

fun main() {

    CoinsChangeMin().apply {
        getWays(7, arrayOf(1, 5, 10, 25)).print()
        getWays(7, arrayOf(2)).print()
        getWays(11, arrayOf(1, 2, 5)).print()
    }

}