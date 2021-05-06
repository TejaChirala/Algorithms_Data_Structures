package arrays_strings

import java.util.*
import kotlin.collections.HashMap

/**
 * Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 **/
class RotateMatrix {

    fun rotateMatrix(array: Array<Array<Int>>): Array<Array<Int>> {

        val n = array.size
        if (n == 1) {
            return array
        }

        for (layer in 0..n / 2) {
            val first = layer
            val last = n - 1 - layer
            for (i in first until last) {
                //temp = ti
                val temp = array[first][i]

                //ti = l(n-i)
                array[first][i] = array[n - 1 - i][first]

                //l(n-i) = b(n-i)
                array[n - 1 - i][first] = array[last][n - 1 - i]

                //b(n - i) = ri
                array[last][n - 1 - i] = array[i][last]

                //ri = temp
                array[i][last] = temp
            }
        }
        return array

    }

}

fun Array<Array<Int>>.print() {

    forEach { array ->
        array.forEach {
            print("$it ")
        }
        println("")
    }
    println("-----------------------------")

}

fun main() {
    RotateMatrix().apply {

        //1 X 1
        rotateMatrix(
            arrayOf(
                arrayOf(1)
            )
        ).print()

        //2 X 2
        rotateMatrix(
            arrayOf(
                arrayOf(1, 2),
                arrayOf(3, 4)
            )
        ).print()

        //3 X 3
        rotateMatrix(
            arrayOf(
                arrayOf(1, 2, 3),
                arrayOf(4, 5, 6),
                arrayOf(7, 8, 9)
            )
        ).print()

        //4 X 4
        rotateMatrix(
            arrayOf(
                arrayOf(1, 2, 3, 4),
                arrayOf(5, 6, 7, 8),
                arrayOf(9, 10, 11, 12),
                arrayOf(13, 14, 15, 16)
            )
        ).print()
    }
}