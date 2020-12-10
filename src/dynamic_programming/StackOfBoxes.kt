package dynamic_programming

import extensions.print
import kotlin.math.max

/**
 * Stack of Boxes: You have a stack of n boxes, with widths Wi ' heights hi ' and depths di . The boxes
 * cannot be rotated and can only be stacked on top of one another if each box in the stack is strictly
 * larger than the box above it in width, height, and depth. Implement a method to compute the
 * height of the tallest possible stack. The height of a stack is the sum of the heights of each box.
 **/
class StackOfBoxes {

    var maxHeight = 0

    fun getLongestHeight(boxes: ArrayList<Box>): Int {
        val map = HashMap<Int, Int>()
        getLongestHeight(boxes, map, 0, boxes[0].h)
        return maxHeight
    }

    private fun getLongestHeight(boxes: ArrayList<Box>, map: HashMap<Int, Int>, index: Int, height: Int): Int {

        maxHeight = max(maxHeight, height)

        val base = boxes[index]
        var h = base.h
        for (i in boxes.indices) {

            if (base > boxes[i]) {
                val newHeight = if (map.containsKey(i)) {
                    map[i]!!
                } else {
                    getLongestHeight(boxes, map, i, height + boxes[i].h)
                }
                h = max(h, base.h + newHeight)
            }
        }
        map[index] = h
        return h

    }

    data class Box(val h: Int, val w: Int, val d: Int) {
        operator fun compareTo(box: Box): Int {
            return if (h > box.h && w > box.w && d > box.d) return 1 else -1
        }
    }

}

fun main() {
    StackOfBoxes().apply {
        getLongestHeight(
                arrayListOf(
                        StackOfBoxes.Box(4, 4, 4),
                        StackOfBoxes.Box(3, 3, 3),
                        StackOfBoxes.Box(2, 2, 2),
                        StackOfBoxes.Box(2, 2, 2)
                )
        ).print()
    }
}