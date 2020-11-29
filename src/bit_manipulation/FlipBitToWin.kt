package bit_manipulation

import extensions.print
import kotlin.math.max

/**
 * Flip Bit to Win: You have an integer and you can flip exactly one bit from a 13 to a 1. Write code to
 * find the length of the longest sequence of ls you could create.
 * EXAMPLE
 * Input: 1775 (or: 1113111131111)
 * Output: 8
 **/
class FlipBitToWin {

    fun flipBitToWin(number: Int): Int {

        var num = number
        var currentLength = 0
        var maxLength = 1
        var allowedOneCount = 1
        var endSequence = false
        while (num > 0) {

            val bit = num and 1
            if (bit == 0) {
                if (allowedOneCount == 0) {
                    endSequence = true
                } else {
                    allowedOneCount = 0
                    val nextBit = if (num and 2 != 0) 1 else 0
                    if (nextBit == 0) {
                        endSequence = true
                    }
                    currentLength++
                }

            } else {
                currentLength++
            }
            maxLength = max(currentLength, maxLength)

            if (endSequence) {
                allowedOneCount = 1
                endSequence = false
                currentLength = 0
            }

            num = num shr 1

        }
        return maxLength

    }

}

fun main() {
    FlipBitToWin().apply {
        flipBitToWin(0).print()
        flipBitToWin(1).print()
        flipBitToWin(2).print()
        flipBitToWin(3).print()
        flipBitToWin(4).print()
        flipBitToWin(5).print()
        flipBitToWin(6).print()
        flipBitToWin(1775).print()
    }
}