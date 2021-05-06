package moderate


/**
 * Bisect Squares: Given two squares on a two-dimensional plane, find a line that would cut these two
 * squares in half. Assume that the top and the bottom sides of the square run parallel to the x-axis.
 **/
class BisectSquares {

    fun getLine(s1: Square, s2: Square): Line? {

        if (s1.topLeftPoint.x > s2.topLeftPoint.x) swap(s1, s2)

        if (s1.midPoint.x == s2.midPoint.x || s1.midPoint.y == s2.midPoint.y) {
            return Line(s1.midPoint, s2.midPoint)
        }

        if (s1.midPoint.x == s2.midPoint.x && s1.midPoint.y == s2.midPoint.y) {
            return Line(s1.midPoint, Point(s2.topLeftPoint.x + s2.sideSize / 2, s2.topLeftPoint.y))
        }

        return null

    }

    private fun swap(s1: Square, s2: Square) {
        s1.setSquare(s2).also { s2.setSquare(s1) }
    }

    private fun swap(p1: Point, p2: Point) {
        p1.setLocation(p2).also { p2.setLocation(p1) }
    }

    private fun isBetween(start: Intersection.Point, middle: Intersection.Point, end: Intersection.Point): Boolean {
        return isBetween(start.x, middle.x, end.x) && isBetween(start.y, middle.y, end.y)
    }

    private fun isBetween(start: Double, middle: Double, end: Double): Boolean {
        return if (start < end) {
            middle in start..end
        } else {
            middle in end..start
        }
    }

    class Square(private var _topLeftPoint: Point, private var _sideSize: Int) {
        private var _midPoint: Point

        val midPoint: Point
        get() = _midPoint

        val topLeftPoint: Point
        get() = _topLeftPoint

        val sideSize: Int
        get() = _sideSize


        init {
            _midPoint = Point(
                    topLeftPoint.x + sideSize / 2,
                    topLeftPoint.y + sideSize / 2
            )
        }

        fun setSquare(s: Square) {
            _topLeftPoint = s.topLeftPoint
            _sideSize = s.sideSize
            _midPoint = s.midPoint
        }

    }

    class Line(val start: Point, val end: Point) {

        val a: Double
        val b: Double
        val c: Double

        init {

            when {
                start.x == end.x -> {
                    a = 1.0
                    b = 0.0
                    c = start.x
                }
                start.y == end.y -> {
                    a = 0.0
                    b = 1.0
                    c = start.y
                }
                else -> {
                    val deltaY = end.y - start.y
                    val deltaX = end.x - start.x

                    // y = mx + b
                    // mx - y = -b => a = m, b = -1, c = -b
                    a = deltaY / deltaX
                    b = -1.0
                    c = (end.y - (a * end.x)) * -1
                }
            }
        }

    }

    class Point(private var _x: Double, private var _y: Double) {
        val x: Double
            get() = _x

        val y: Double
            get() = _y

        fun setLocation(p: Point) {
            _x = p.x
            _y = p.y
        }

        fun print() {
            println("($_x, $_y)")
        }

    }


}

fun main() {
    BisectSquares().apply {

    }
}