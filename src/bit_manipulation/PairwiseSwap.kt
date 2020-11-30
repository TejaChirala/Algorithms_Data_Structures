package bit_manipulation

import extensions.print

/**
 * Pairwise Swap: Write a program to swap odd and even bits in an integer with as few instructions as
 * possible (e.g., bit 13 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).
 **/
class PairwiseSwap {

    fun swap(number: Int): Int {
        return (number and (0X55555555.inv()) shr 1) or
                ((number and (0X55555555)) shl 1)
    }

}

fun main() {
    PairwiseSwap().apply {
        Integer.toBinaryString(23).print()
        Integer.toBinaryString(swap(23)).print()
    }
}