package bit_manipulation

import extensions.print

/**
 * Insertion: You are given two 32-bit numbers, N and M, and two bit positions, i and j.
 * Write a method to insert M into N such that M starts at bit j and ends at bit i. You
 * can assume that the bits j through i have enough space to fit all of M. That is, if
 * M = 10011, you can assume that there are at least 5 bits between j and i. You would not, for
 * example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
 * EXAMPLE
 * Input: N = 10000000000, M = 10011, i = 2, j = 6
 * Output: N = 10001001100
 **/
class Insertion {

    fun insert(m: Int, n: Int, i: Int, j: Int): Int {
        //Clear the j to i area with 0's
        // We need value with 1's followed by 0's (from j till i) followed by 1's
        val mask = (-1 shl (j + 1)) or ((1 shl i) - 1)
        return (n and mask) or (m shl i)
    }

}

fun main() {
    Insertion().apply {
        insert(19, 1024, 2, 6).print()
    }
}