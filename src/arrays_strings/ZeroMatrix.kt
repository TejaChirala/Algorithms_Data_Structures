package arrays_strings

/**
 * Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
 * column are set to O.
 **/
class ZeroMatrix {

    fun makeZeroMatrix(array: Array<Array<Int>>, columnCount: Int): Array<Array<Int>> {

        val zeroRows = mutableSetOf<Int>()
        val zeroColumns = mutableSetOf<Int>()

        for (i in array.indices) {
            for (j in 0 until columnCount) {
                if (array[i][j] == 0) {
                    zeroRows.add(i)
                    zeroColumns.add(j)
                }
            }
        }

        zeroRows.forEach {
            for (i in 0 until columnCount) {
                array[it][i] = 0
            }
        }

        zeroColumns.forEach {
            for (i in array.indices) {
                array[i][it] = 0
            }
        }

        return array
    }

    fun makeZeroMatrixWithLessSpace(array: Array<Array<Int>>, columnCount: Int): Array<Array<Int>> {

        var firstRowHasZeroes = false
        var firstColumnHasZeroes = false

        for (i in array.indices) {
            if (array[i][0] == 0) {
                firstColumnHasZeroes = true
                break
            }
        }

        for (i in array[0].indices) {
            if (array[0][i] == 0) {
                firstRowHasZeroes = true
                break
            }
        }

        for (i in 1 until array.size) {
            for (j in 1 until columnCount) {
                if (array[i][j] == 0) {
                    array[i][0] = 0
                    array[0][j] = 0
                }
            }
        }

        for (i in array.indices) {
            if (array[i][0] == 0) {
                makeRowZeroes(array, i)
            }
        }

        for (i in array[0].indices) {
            if (array[0][i] == 0) {
                makeColumnZeroes(array, i)
            }
        }

        if (firstRowHasZeroes) {
            makeRowZeroes(array, 0)
        }

        if (firstColumnHasZeroes) {
            makeColumnZeroes(array, 0)
        }

        return array
    }

    private fun makeRowZeroes(array: Array<Array<Int>>, row: Int) {
        for (i in array[0].indices) {
            array[row][i] = 0
        }
    }

    private fun makeColumnZeroes(array: Array<Array<Int>>, column: Int) {
        for (i in array.indices) {
            array[i][column] = 0
        }
    }

}

fun main() {
    ZeroMatrix().apply {

        makeZeroMatrix(
            arrayOf(
                arrayOf(1, 2, 0),
                arrayOf(3, 4, 5)
            ),
            3
        ).print()

        makeZeroMatrix(
            arrayOf(
                arrayOf(1, 2, 0, 6),
                arrayOf(3, 4, 5, 7),
                arrayOf(1, 4, 6, 7)
            ),
            4
        ).print()

        makeZeroMatrixWithLessSpace(
            arrayOf(
                arrayOf(1, 2, 0),
                arrayOf(3, 4, 5)
            ),
            3
        ).print()

        makeZeroMatrixWithLessSpace(
            arrayOf(
                arrayOf(1, 2, 0, 6),
                arrayOf(3, 4, 5, 7),
                arrayOf(1, 4, 6, 7)
            ),
            4
        ).print()

    }
}