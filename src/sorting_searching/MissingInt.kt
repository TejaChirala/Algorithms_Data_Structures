package sorting_searching

import extensions.print
import java.io.File
import kotlin.experimental.and
import kotlin.experimental.or

/**
 * Missing Int: Given an input file with four billion non-negative integers, provide an algorithm to
 * generate an integer that is not contained in the file. Assume you have 1 GB of memory available for
 * this task.
 * FOLLOW UP
 * What if you have only 10MB of memory? Assume that all the values are distinct and we now have
 * no more than one billion non-negative integers.
 **/
class MissingInt {

    fun getInt(file: File): Int {
        val byteArray = ByteArray(Integer.MAX_VALUE / 8)

        file.readLines().forEach { string ->
            val ints = string.split(" ")
            ints.forEach {
                val int = it.toInt()
                byteArray[int / 8] = byteArray[int / 8] or ((1 shl (7 - int % 8)).toByte())
            }
        }

        byteArray.forEachIndexed { index, byte ->

            for (i in 7 downTo 0) {
                val bit = ((byte and (1 shl i).toByte()) != 0.toByte())
                if (!bit) {
                    return (index * 8) + 7 - i
                }
            }

        }

        return -1
    }

    /**
     * FOLLOW UP
     * What if you have only 10MB of memory? Assume that all the values are distinct and we now have
     * no more than one billion non-negative integers.
     **/
    fun getIntWithLimitedSpace(file: File, space: Int = 10 * 1024 * 1024 * 8): Int {

        var rangeStart = 0
        var rangeEnd = space
        var rangeOffset = 0
        var byteArray = ByteArray((rangeEnd - rangeStart) / 8)

        readFromFile(file, byteArray, rangeStart, rangeEnd, 0)

        if (rangeItems == rangeEnd - rangeStart) {
            resetRangeItemCount()
            rangeEnd = 24//Integer.MAX_VALUE
            rangeStart = rangeEnd - space
            byteArray = ByteArray((rangeEnd - rangeStart) / 8)
            readFromFile(file, byteArray, rangeStart, rangeEnd, rangeStart)
            rangeOffset = rangeStart
        }

        byteArray.forEachIndexed { index, byte ->

            for (i in 7 downTo 0) {
                val bit = ((byte and (1 shl i).toByte()) != 0.toByte())
                if (!bit) {
                    return (index * 8) + 7 - i + rangeOffset
                }
            }

        }

        return -1
    }


    private fun readFromFile(file: File, byteArray: ByteArray, rangeStart: Int, rangeEnd: Int, rangeOffset: Int) {
        file.readLines().forEach { string ->
            val ints = string.split(" ")
            ints.forEach {
                val int = it.toInt()
                markBit(byteArray, int, rangeStart, rangeEnd, rangeOffset)
            }
        }
    }

    private fun markBit(array: ByteArray, number: Int, rangeStart: Int, rangeEnd: Int, rangeOffset: Int) {
        if (number in rangeStart..rangeEnd) {
            val value = (number - rangeOffset) / 8
            incrementRangeItemCount()
            array[value] = array[value] or ((1 shl (7 - number % 8)).toByte())
        }
    }

    private var rangeItems = 0

    private fun incrementRangeItemCount() = rangeItems++

    private fun resetRangeItemCount() {
        rangeItems = 0
    }

}

fun main() {

    MissingInt().apply {
        getInt(
                File("s").apply {
                    writeText("1 2 3 4 7 9")
                }
        ).print()
        getInt(
                File("s").apply {
                    writeText("0 1 2 3 4 7 9")
                }
        ).print()
        getInt(
                File("s").apply {
                    writeText("0 1 2 3 4 5 6 7 9")
                }
        ).print()
        getInt(
                File("s").apply {
                    writeText("0 1 2 3 4 5 6 7 8 9")
                }
        ).print()
        getIntWithLimitedSpace(
                File("s").apply {
                    writeText("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15")
                }, 16
        ).print()
    }

}