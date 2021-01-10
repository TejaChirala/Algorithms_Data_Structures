package hard

import extensions.print
import kotlin.math.sqrt

/**
 * Missing Two: You are given an array with all the numbers from 1 to N appearing exactly once,
 * except for one number that is missing. How can you find the missing number in 0 (N) time and
 * 0(1) space? What if there were two numbers missing?
 **/
class MissingTwo {

    fun findMissingNumber(a: IntArray): Int {
        val n = a.size + 1
        val actualCount: Int = (n * (n + 1)) / 2
        var currentCount = 0
        a.forEach {
            currentCount += it
        }
        return actualCount - currentCount
    }

    fun findMissingTwoNumbers(a: IntArray): Pair<Int, Int> {
        val n = a.size + 2
        var totalSumTillN: Int = (n * (n + 1)) / 2
        var totalSumOfSquares = getSumOfSquares(n)
        a.forEach {
            totalSumTillN -= it
            totalSumOfSquares -= (it * it)
        }
        val x = solveEquation(totalSumTillN, totalSumOfSquares)
        return Pair(x, totalSumTillN - x)
    }

    private fun solveEquation(sum: Int, sumOfSquares: Int): Int {
        /*
        * x^2 + y^2 = sumOfSquares
        * x + y = sum => y = sum - x
        * X^2 + (sum - x)^2 = sumOfSquares
        * Final equal is: 2x^2 - 2 * sum * x + (sum^2 - sumOfSquares) = 0
        * Formula to find x:
        * x = (-b +- sqrt(b^2 - 4ac))/2a
        **/
        val a = 2
        val b = -2 * sum
        val c = (sum * sum) - sumOfSquares

        return (((-1 * b) + sqrt(((b * b) - (4 * a * c)).toDouble())) / (2 * a)).toInt()
    }

    private fun getSumOfSquares(n: Int): Int {
        var sum = 0
        for (i in 1..n) {
            sum += (i * i)
        }
        return sum
    }

}

fun main() {
    MissingTwo().apply {
        findMissingNumber(intArrayOf(1, 2, 4, 5, 6)).print()
        findMissingTwoNumbers(intArrayOf(1, 4)).print()
        findMissingTwoNumbers(intArrayOf(1, 2, 4, 5, 6, 8)).print()
    }
}