package new_work.dynamic_programming

import extensions.print
import java.awt.Point

class RobotInGrid {

    fun findPath(a: Array<BooleanArray>): List<Point> {
        val result = ArrayList<Point>()
        val pathFound = findPath(a, 0, 0, result)
        return if (pathFound) result else emptyList()
    }

    private fun findPath(a: Array<BooleanArray>, r: Int, c: Int, result: ArrayList<Point>): Boolean {
        if (r !in a.indices || c !in 0 until a[1].size) return false
        if (!a[r][c]) return false
        if (r == a.size - 1 && c == a[1].size - 1) {
            result.add(Point(r, c))
            return true
        }
        a[r][c] = false
        result.add(Point(r, c))
        return findPath(a, r, c + 1, result) || findPath(a, r + 1, c, result)
    }

}

fun main() {
    RobotInGrid().apply {
        findPath(arrayOf(
                booleanArrayOf(true, false, true, true),
                booleanArrayOf(true, false, true, true),
                booleanArrayOf(true, false, true, true),
                booleanArrayOf(true, true, true, true)
        )).print()
        findPath(arrayOf(
                booleanArrayOf(true, false, true, true),
                booleanArrayOf(true, false, true, true),
                booleanArrayOf(true, false, true, true),
                booleanArrayOf(true, false, true, true)
        )).print()
        findPath(arrayOf(
                booleanArrayOf(true, false, true, true),
                booleanArrayOf(true, false, true, true),
                booleanArrayOf(true, false, true, true),
                booleanArrayOf(true, true, true, false)
        )).print()
    }
}