package moderate

/**
 * Intersection: Given two straight line segments (represented as a start point and an end point),
 * compute the point of intersection, if any.
 **/
class Intersection {

    //@see https://www.geeksforgeeks.org/program-for-point-of-intersection-of-two-lines/
    //For further details
    fun getIntersectionDet(start1: Point, end1: Point, start2: Point, end2: Point): Point? {

        if (start1.x > end1.x) swap(start1, end1)
        if (start2.x > end2.x) swap(start2, end2)
        if (start1.x > start2.x) {
            swap(start1, start2)
            swap(end1, end2)
        }

        //equation is a1x+b1y = c1
        val a1 = end1.y - start1.y
        val b1 = start1.x - end1.x
        val c1 = a1 * start1.x + b1 * start1.y

        //equation is a2x+b2y = c2
        val a2 = end2.y - start2.y
        val b2 = start2.x - end2.x
        val c2 = a2 * start2.x + b2 * start2.y

        val determinant = a1 * b2 - a2 * b1

        //Lines are parallel
        if (determinant == 0.0) {
            return if (isBetween(start1, start2, end1)) start2 else null
        }

        val x = (b2 * c1 - b1 * c2) / determinant
        val y = (a1 * c2 - a2 * c1) / determinant
        val intersection = Point(x, y)

        return if (isBetween(start1, intersection, end1) && isBetween(start2, intersection, end2)) {
            intersection
        } else {
            null
        }

    }

    // This function won't work for vertical lines
    fun getIntersection(start1: Point, end1: Point, start2: Point, end2: Point): Point? {

        if (start1.x > end1.x) swap(start1, end1)
        if (start2.x > end2.x) swap(start2, end2)
        if (start1.x > start2.x) {
            swap(start1, start2)
            swap(end1, end2)
        }

        val line1 = Line(start1, end1)
        val line2 = Line(start2, end2)

        //If two lines are on the same line, check if start of second line is on the first line
        if (line1.slope == line2.slope && line1.yIntercept == line2.yIntercept) {
            return if (isBetween(start1, start2, end1)) start2 else null
        }

        //Calculating the intersection point
        val x = (line2.yIntercept - line1.yIntercept) / (line1.slope - line2.slope)
        val y = x * line1.slope + line1.yIntercept
        val intersection = Point(x, y)

        return if (isBetween(start1, intersection, end1) && isBetween(start2, intersection, end2)) {
            intersection
        } else {
            null
        }

    }

    private fun isBetween(start: Point, middle: Point, end: Point): Boolean {
        return isBetween(start.x, middle.x, end.x) && isBetween(start.y, middle.y, end.y)
    }

    private fun isBetween(start: Double, middle: Double, end: Double): Boolean {
        return if (start < end) {
            middle in start..end
        } else {
            middle in end..start
        }
    }

    private fun swap(p1: Point, p2: Point) {
        p1.setLocation(p2).also { p2.setLocation(p1) }
    }

    class Line(val start: Point, val end: Point) {

        val yIntercept: Double
        val slope: Double

        init {
            val deltaY = end.y - start.y
            val deltaX = end.x - start.x
            slope = if (deltaX == 0.0) {
                Double.MAX_VALUE
            } else {
                deltaY / deltaX
            }
            yIntercept = end.y - slope * end.x // y = mx + b => b = y - mx
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

    Intersection().apply {
        getIntersectionDet(
                start1 = Intersection.Point(1.0, 4.0),
                end1 = Intersection.Point(6.0, 4.0),
                start2 = Intersection.Point(3.0, 3.0),
                end2 = Intersection.Point(3.0, 6.0)
        )?.print()
        getIntersectionDet(
                start1 = Intersection.Point(1.0, 4.0),
                end1 = Intersection.Point(6.0, 4.0),
                start2 = Intersection.Point(3.0, 4.0),
                end2 = Intersection.Point(3.0, 6.0)
        )?.print()
        getIntersectionDet(
                start1 = Intersection.Point(1.0, 4.0),
                end1 = Intersection.Point(6.0, 4.0),
                start2 = Intersection.Point(4.0, 4.0),
                end2 = Intersection.Point(10.0, 4.0)
        )?.print()
    }

}