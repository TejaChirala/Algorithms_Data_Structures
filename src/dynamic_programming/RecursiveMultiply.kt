package dynamic_programming

import extensions.print
import kotlin.math.max
import kotlin.math.min

/**
 * Recursive Multiply: Write a recursive function to multiply two positive integers without using the
 * operator. You can use addition, subtraction, and bit shifting, but you should minimize the number
 * of those operations.
 **/
class RecursiveMultiply {

    fun multiply(num1: Int, num2: Int): Int {
        return multipleHelper(
                min(num1, num2),
                max(num1, num2)
        )
    }

    private fun multipleHelper(small: Int, big: Int): Int {

        if (small == 0) return 0
        if (small == 1) {
            return big
        }
        val half = small shr 1
        val result = multipleHelper(half, big)
        return if (small % 2 == 0) {
            result + result
        } else {
            result + result + big
        }
    }

}

fun main() {
    RecursiveMultiply().apply {
        multiply(4, 10).print()
        multiply(5, 10).print()
    }
}