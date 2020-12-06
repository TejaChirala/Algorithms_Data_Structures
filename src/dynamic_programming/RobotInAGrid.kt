package dynamic_programming

import extensions.print
/**
 * Robot in a Grid: Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
 * The robot can only move in two directions, right and down, but certain cells are "off limits" such that
 * the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
 * the bottom right.
 **/
class RobotInAGrid {

    companion object {
        private const val B = "B"
        private const val R = "R"
    }

    fun findPath(a: Array<BooleanArray>, columns: Int): String? {
        return findPath(a, "", 0, 0, a.size, columns).path
    }

    private fun findPath(a: Array<BooleanArray>, path: String, x: Int, y: Int, rows: Int, columns: Int): Result {
        print("($y,$x)")
        when {
            (y == rows - 1 && x == columns - 1) -> return Result(path, true)
            (y > rows - 1 || x > columns - 1) -> return Result(null, false)
            !a[y][x] -> return Result(null, false)
        }

        val resultRight = findPath(a, path = path + R, x + 1, y, rows, columns)
        if (resultRight.path != null && resultRight.isSuccess) {
            return resultRight
        }

        val resultBottom = findPath(a, path = path + B, x, y + 1, rows, columns)
        if (resultBottom.path != null && resultBottom.isSuccess) {
            return resultBottom
        }

        return Result(null, false)
    }

    data class Result(val path: String?, val isSuccess: Boolean)

}

fun main() {

    RobotInAGrid().apply {
        findPath(arrayOf(
                booleanArrayOf(true, false, true, false),
                booleanArrayOf(true, true, false, false),
                booleanArrayOf(false, true, true, true)
        ), 4).print()
        findPath(arrayOf(
                booleanArrayOf(true, false, true),
                booleanArrayOf(true, false, true),
                booleanArrayOf(true, true, true)
        ), 3).print()
        findPath(arrayOf(
                booleanArrayOf(true, false, true),
                booleanArrayOf(true, false, true),
                booleanArrayOf(true, false, true)
        ), 3).print()
    }

}