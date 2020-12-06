package dynamic_programming

import extensions.print

/**
 * Triple Step: A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3
 * steps at a time. Implement a method to count how many possible ways the child can run up the
 * stairs.
 **/
class TripleSet {

    fun getPossibleWaysCount(n: Int): Int {
        if (n <= 0) return 0
        when (n) {
            1 -> return 1
            2 -> return 2
            3 -> return 4
        }
        val a = IntArray(n)
        a[0] = 1
        a[1] = 2
        a[2] = 4

        for (i in 3 until n) {
            if (a[i] == 0) {
                a[i] = a[i - 1] + a[i - 2] + a[i - 3]
            }
        }
        return a[n - 1]
    }

}

fun main() {

    TripleSet().apply {
        getPossibleWaysCount(-1).print()
        getPossibleWaysCount(0).print()
        getPossibleWaysCount(1).print()
        getPossibleWaysCount(2).print()
        getPossibleWaysCount(3).print()
        getPossibleWaysCount(4).print()
        getPossibleWaysCount(5).print()
        getPossibleWaysCount(6).print()
        getPossibleWaysCount(36).print()
    }

}