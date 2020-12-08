package dynamic_programming

import extensions.print
import java.awt.Point


class PaintFill {

    enum class Color {
        R, B, G, W
    }

    fun fill(array: Array<Array<Color>>, point: Point, columns: Int, newColor: Color) {
        if (array[point.x][point.y] == newColor) return
        fill(array, point, columns, array[point.x][point.y], newColor)
    }

    private fun fill(array: Array<Array<Color>>, point: Point, columns: Int, oldColor: Color, newColor: Color) {

        val row = point.x
        val column = point.y
        if ((row < 0 || row >= array.size) || (column < 0 || column >= columns)) return

        if (array[row][column] == oldColor) {
            array[row][column] = newColor
            var p = Point(row, column - 1)
            fill(array, p, columns, oldColor, newColor)
            p = Point(row - 1, column)
            fill(array, p, columns, oldColor, newColor)
            p = Point(row, column + 1)
            fill(array, p, columns, oldColor, newColor)
            p = Point(row + 1, column)
            fill(array, p, columns, oldColor, newColor)
        }
    }

}

fun main() {
    PaintFill().apply {

        val w = PaintFill.Color.W
        val r = PaintFill.Color.R
        val b = PaintFill.Color.B
        val g = PaintFill.Color.G
        var a = arrayOf(
                arrayOf(w, w, w, w, w, w, w, w),
                arrayOf(w, w, w, w, w, w, w, w),
                arrayOf(w, w, w, w, w, w, w, w),
                arrayOf(w, w, w, w, w, w, w, w),
                arrayOf(w, w, w, w, w, w, w, w),
                arrayOf(w, w, w, w, w, w, w, w),
                arrayOf(w, w, w, w, w, w, w, w),
                arrayOf(w, w, w, w, w, w, w, w),
        )

        fill(
                array = a,
                point = Point(3, 4),
                columns = 8,
                newColor = g
        )
        a.print()

        a = arrayOf(
                arrayOf(w, w, w, w, w, w, w, w),
                arrayOf(w, w, w, w, w, w, w, w),
                arrayOf(w, w, w, w, w, w, w, w),
                arrayOf(w, w, w, r, w, w, r, w),
                arrayOf(w, w, w, r, w, w, r, w),
                arrayOf(w, w, w, r, b, b, r, w),
                arrayOf(w, w, w, w, b, b, w, w),
                arrayOf(w, w, w, w, b, b, w, w),
        )

        fill(
                array = a,
                point = Point(4, 4),
                columns = 8,
                newColor = g
        )
        a.print()


    }
}