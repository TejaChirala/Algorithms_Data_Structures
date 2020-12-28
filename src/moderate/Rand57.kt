package moderate

import extensions.print
import kotlin.random.Random

/**
 * Rand7 from RandS: Implement a method rand7() given rand5 (). That is, given a method that
 * generates a random number between 0 and 4 (inclusive), write a method that generates a random
 * number between 0 and 6 (inclusive).
 **/
class Rand57 {

    fun rand7(): Int {
        var n = 25
        while (n > 21) {
            n = 5 * rand5() + rand5()
        }
        return n % 7
    }

    private fun rand5(): Int {
        return Random.nextInt(0, 5)
    }

}

fun main() {

    Rand57().apply {
        rand7().print()
        rand7().print()
        rand7().print()
        rand7().print()
        rand7().print()
        rand7().print()
        rand7().print()
        rand7().print()
        rand7().print()
        rand7().print()
        rand7().print()
        rand7().print()
        rand7().print()
        rand7().print()
        rand7().print()
    }

}