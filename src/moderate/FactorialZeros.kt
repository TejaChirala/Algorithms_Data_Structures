package moderate

import extensions.print

/**
 * Factorial Zeros: Write an algorithm which computes the number of trailing zeros in n factorial.
 **/
class FactorialZeros {

    fun getZeroesCount(num: Int): Int {
        var n = num
        var count = 0
        var i = 5
        do {
            count += n / 5
            n /= 5
            i *= 5
        } while (n % i > 0)
        
        return count
    }

}

fun main() {
    FactorialZeros().apply {
        getZeroesCount(5).print()
        getZeroesCount(29).print()
    }
}