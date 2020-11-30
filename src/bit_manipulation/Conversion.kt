package bit_manipulation

import extensions.print

/**
 * Conversion: Write a function to determine the number of bits you would need to flip to convert
 * integer A to integer B.
 * EXAMPLE
 * Input:  29 (or: 111131), 15 (or: 131111)
 * Output: 2
 **/
class Conversion {

    fun conversionCount(num1: Int, num2: Int): Int {
        var count = 0
        var c = num1 xor num2
        while (c != 0) {
            count++
            c = c and (c - 1)
        }
        return count
    }

    /**
     * function to count no.of 1's in O(b)
     * where b is the no.of 1's present
     **/
    fun oneCount(num: Int): Int {
        var count = 0
        var c = num
        while (c != 0) {
            count++
            c = c and (c - 1)
        }
        return count
    }

}

fun main() {
    Conversion().apply {
        conversionCount(29, 15).print()
        conversionCount(16, 10).print()
        conversionCount(1, 1).print()
        conversionCount(8, 15).print()
        oneCount(1775).print() //11011101111
        oneCount(-1).print()
    }
}