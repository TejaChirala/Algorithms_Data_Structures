package bit_manipulation

import extensions.print
import kotlin.experimental.or

class DrawLine {

    fun drawLine(screen: ByteArray, width: Int, x1: Int, x2: Int, y: Int): ByteArray {

        val noOfBytesPerRow = width / 8
        val startIndexOfY = noOfBytesPerRow * y
        val endIndexOfY = startIndexOfY + noOfBytesPerRow - 1
        draw(startIndex = x1 % 8,
                startArrayIndex = startIndexOfY,
                endIndex = x2 % 8,
                endArrayIndex = endIndexOfY,
                screen = screen)
        return screen
    }

    private fun draw(startIndex: Int, startArrayIndex: Int,
                     endIndex: Int, endArrayIndex: Int, screen: ByteArray) {
        val arraysToBeUpdated = endArrayIndex - startArrayIndex + 1
        if (arraysToBeUpdated == 1) {

            if (startIndex == 0 && endIndex == 7) {
                drawFull(startArrayIndex, screen)
            } else if (startIndex == 0) {
                draw0TillI(startArrayIndex, startIndex, screen)
            } else if (endIndex == 7) {
                drawITillEnd(startArrayIndex, startIndex, screen)
            } else {
                drawITillJ(startArrayIndex, startIndex, endIndex, screen)
            }

        } else {

            drawITillEnd(startArrayIndex, startIndex, screen)
            var index = startArrayIndex + 1
            repeat(arraysToBeUpdated - 2) {
                drawFull(index, screen)
                index++
            }
            draw0TillI(index, endIndex, screen)

        }
    }

    private fun drawITillJ(arrayIndex: Int, i: Int, j: Int, screen: ByteArray) {
        val noOfOnes = j - i
        screen[arrayIndex] = screen[arrayIndex] or (((1 shl noOfOnes + 1) - 1) shl (8 - j - 1)).toByte()
    }

    private fun drawFull(arrayIndex: Int, screen: ByteArray) {
        screen[arrayIndex] = 255.toByte()
    }

    private fun drawITillEnd(arrayIndex: Int, i: Int, screen: ByteArray) {
        val num = 8 - i - 1
        screen[arrayIndex] = screen[arrayIndex] or ((1 shl (num + 1)) - 1).toByte()
    }

    private fun draw0TillI(arrayIndex: Int, i: Int, screen: ByteArray) {
        val num = 8 - i - 1
        screen[arrayIndex] = screen[arrayIndex] or (-1 shl num).toByte()
    }

}

fun main() {
    DrawLine().apply {
        val a = drawLine(byteArrayOf(0, 0, 0, 0, 0, 0, 0, 0), 8, 2, 5, 5)
        a[5].print()
        val b = drawLine(
                byteArrayOf(0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0), 24, 3, 20, 5)
        b[15].print()
        b[16].print()
        b[17].print()
    }
}