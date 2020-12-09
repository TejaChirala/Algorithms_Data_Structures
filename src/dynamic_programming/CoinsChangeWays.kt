package dynamic_programming

import extensions.print

/**
 * Coins: Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and
 * pennies (1 cent), write code to calculate the number of ways of representing n cents.
 * @link https://www.youtube.com/watch?v=jaNZ83Q3QGc
 * */
class CoinsChangeWays {

    fun getWays(num: Int, d: Array<Int>): Int {

        val array = Array(num + 1) {
            if (it == 0) 1 else 0
        }

        d.forEach { denom ->

            for (i in denom until array.size) {
                val index = i - denom
                array[i] += array[index]
            }

        }
        return array[num]

    }

}

fun main() {

    CoinsChangeWays().apply {
        getWays(7, arrayOf(1, 5, 10, 25)).print()
    }

}