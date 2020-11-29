package bit_manipulation

import extensions.print

/**
 * Next Number: Given a positive integer, print the next smallest and the next largest number that\
 * have the same number of 1 bits in their binary representation.
 **/
class NextNumber {

    fun getNextNumbers(number: Int): Pair<Int, Int> {
        return Pair(getPreviousNumber(number), getNextNumber(number))
    }

    private fun getPreviousNumber(number: Int): Int {
        var c = number
        var c0 = 0
        var c1 = 0

        while (c and 1 == 1) {
            c1++
            c = c shr 1
        }
        if (c == 0) return -1

        while (c and 1 == 0 && c != 0) {
            c0++
            c = c shr 1
        }

        return number and (1 shl c0 + c1).inv() or (1 shl c0 + c1 - 1)
    }

    private fun getNextNumber(number: Int): Int {
        var c = number
        var c0 = 0
        var c1 = 0

        while (c and 1 == 0 && c != 0) {
            c0++
            c = c shr 1
        }

        while (c and 1 == 1) {
            c1++
            c = c shr 1
        }
        if (c0 + c1 == 31 || c0 + c1 == 0) {
            return -1
        }

        return number or (1 shl c0 + c1) and (-1 shl c0 + c1) or ((1 shl c1 - 1) - 1)
    }

}

fun main() {
    NextNumber().apply {
        getNextNumbers(1).print()
        getNextNumbers(5).print()
        getNextNumbers(40).print()
    }
}