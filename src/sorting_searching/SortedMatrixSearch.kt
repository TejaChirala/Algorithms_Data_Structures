package sorting_searching

import extensions.print
import java.awt.Point

/**
 * Sorted Matrix Search: Given an M x N matrix in which each row and each column is sorted in
 * ascending order, write a method to find an element.
 **/
class SortedMatrixSearch {

    fun search(array: Array<Array<Int>>, columns: Int, value: Int): Point {

        var row = 0
        var column = columns - 1

        while (row in 0 until columns && column in array.indices) {

            when {
                array[row][column] == value -> {
                    return Point(row, column)
                }
                array[row][column] > value -> {
                    column--
                }
                else -> {
                    row++
                }
            }

        }
        return Point(-1, -1)

    }

}

fun main() {

    SortedMatrixSearch().apply {

        search(
                arrayOf(
                        arrayOf(1, 2, 3, 4),
                        arrayOf(2, 3, 4, 7),
                        arrayOf(3, 4, 5, 9),
                        arrayOf(4, 5, 6, 10),
                ), 4, 5
        ).print()

    }

}