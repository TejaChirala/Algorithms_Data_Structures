package bit_manipulation

import extensions.print

class BitManipulation {

    /**
     * get the bit at a specific position in the number
     **/
    fun getBit(number: Int, index: Int): Boolean {
        return (number and (1 shl index) != 0)
    }

    /**
     * set a bit to 1 at a specific position in the number
     **/
    fun setBit(number: Int, index: Int): Int {
        return number or (1 shl index)
    }

    /**
     * set a bit to 0 at a specific position in the number
     **/
    fun clearBit(number: Int, index: Int): Int {
        return number and ((1 shl index).inv())
    }

    /**
     * MSB -- i -- 0
     * Clearing the bits from MSB till i (MSB and i inclusive)
     **/
    fun clearMSBThroughI(number: Int, index: Int): Int {
        return number and ((1 shl index) - 1)
    }

    /**
     * MSB -- i -- 0
     * Clearing the bits from i till 0 (i and 0 inclusive)
     **/
    fun clearIThrough0(number: Int, index: Int): Int {
        return number and (-1 shl index + 1)
    }

    /**
     * set a bit to given bit at a specific position in the number
     **/
    fun updateBit(number: Int, index: Int, isBitOne: Boolean): Int {
        val bit = if (isBitOne) 1 else 0
        return number and ((1 shl index) - 1) or (bit shl index)
    }

}

fun main() {

    BitManipulation().apply {
        getBit(1, 1).print()
        getBit(5, 0).print()
        getBit(5, 1).print()
        getBit(5, 2).print()
        setBit(5, 1).print()
        setBit(5, 0).print()
        clearBit(5, 0).print()
        clearBit(5, 2).print()
        clearMSBThroughI(24, 3).print()
        clearIThrough0(31, 3).print()
        updateBit(1, 0, false).print()
        updateBit(0, 0, true).print()
    }

}