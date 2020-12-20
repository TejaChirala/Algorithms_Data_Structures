package moderate

import extensions.print
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * Operations: Write methods to implement the multiply, subtract, and divide operations for integers.
 * The results of all of these are integers. Use only the add operator.
 **/
class Operations {

    fun multiply(a: Int, b: Int): Int {
        val sign = ((a < 0) && (b < 0)) || ((a > 0) && (b > 0))
        val absA = abs(a)
        val absB = abs(b)
        val small = min(absA, absB)
        val big = max(absA, absB)
        var result = 0
        repeat(small) {
            result += big
        }
        return if(sign) result else negate(result)
    }

    private fun negateSubOptimal(a: Int): Int {
        val unit = if (a < 0) 1 else -1
        var result = 0
        repeat(abs(a)) {
            result += unit
        }
        return result
    }

    //Optimized
    private fun negate(i: Int): Int {
        var a = i
        val unit = if (a < 0) 1 else -1
        var delta = unit
        var result = 0
        while (a != 0) {
            //Delta too high, reset
            if (delta + a > 0 != a > 0) {
                delta = unit
            }
            result += delta
            a += delta
            delta += delta

        }
        return result
    }

    fun subtract(a: Int, b: Int): Int {
        return a + negate(b)
    }

    fun divide(a: Int, b: Int): Int {
        var count = 0
        var divisor = b
        while (a > divisor) {
            divisor += b
            count++
        }
        return count
    }

}

fun main() {
    Operations().apply {
        multiply(4, 3).print()
        multiply(4, -3).print()
        multiply(10, 3).print()
        subtract(4, 3).print()
        subtract(10, 3).print()
        divide(4, 3).print()
        divide(10, 3).print()
    }
}