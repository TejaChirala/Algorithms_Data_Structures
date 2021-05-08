package new_work.arrays_strings

import extensions.print

class RotateMatrix {

    fun rotate(a: Array<Array<Int>>): Array<Array<Int>> {
        val n = a.size - 1
        for (i in 0 until n / 2) {
            for (j in i until n - i) {

                val t = a[i][j]
                a[i][j] = a[i + j][n - i]
                a[i + j][n - i] = a[n - i][n - j]
                a[n - i][n - j] = a[n - j][i]
                a[n - j][i] = t
            }
        }
        return a
    }

}

fun main() {

    RotateMatrix().apply {
        rotate(arrayOf(
                arrayOf(1, 2, 3, 4),
                arrayOf(1, 2, 3, 4),
                arrayOf(1, 2, 3, 4),
                arrayOf(1, 2, 3, 4)
        )).print()
    }
}