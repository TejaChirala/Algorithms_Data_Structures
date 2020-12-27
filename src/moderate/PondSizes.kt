package moderate

import extensions.print

/**
 * Pond Sizes: You have an integer matrix representing a plot of land, where the value at that location
 * represents the height above sea level. A value of zero indicates water. A pond is a region of
 * water connected vertically, horizontally, or diagonally. The size of the pond is the total number of
 * connected water cells. Write a method to compute the sizes of all ponds in the matrix.
 * EXAMPLE
 * Input:
 * 0 2 1 0
 * 0 1 0 1
 * 1 1 0 1
 * 0 1 0 1
 * Output: 2, 4, 1 (in any order)
 **/
class PondSizes {

    fun getSizes(a: Array<Array<Int>>, columns: Int): ArrayList<Int> {
        val result = ArrayList<Int>()

        for (r in a.indices) {
            for (c in 0 until columns) {
                if (a[r][c] == 0) {
                    result.add(getSize(r, c, a, columns))
                }
            }
        }
        return result
    }

    private fun getSize(r: Int, c: Int, a: Array<Array<Int>>, columns: Int): Int {
        if (r !in a.indices || c !in 0 until columns || a[r][c] == -1) return 0
        if (a[r][c] != 0) {
            a[r][c] = -1
            return 0
        }
        a[r][c] = -1
        var size = 1
        for (i in r - 1..r + 1) {
            for (j in c - 1..c + 1) {
                size += getSize(i, j, a, columns)
            }
        }
        return size
    }

}

fun main() {
    PondSizes().apply {
        getSizes(
                arrayOf(
                        arrayOf(0, 2, 1, 0),
                        arrayOf(0, 1, 0, 1),
                        arrayOf(1, 1, 0, 1),
                        arrayOf(0, 1, 0, 1)
                ),
                4
        ).print()
    }
}