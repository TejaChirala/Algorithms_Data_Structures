package hard

import extensions.print
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min

/**
 * Volume of Histogram: Imagine a histogram (bar graph). Design an algorithm to compute the
 * volume of water it could hold if someone poured water across the top. You can assume that each
 * histogram bar has width 1.
 * EXAMPLE (Black bars are the histogram. Gray is water.)
 * Input: {0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 5, 0, 1, 0, 0, 0}
 **/
class VolumeOfHistogram {

    fun find(a: IntArray): Int {
        val leftMax = IntArray(a.size) { 0 }
        fillLeftMax(a, leftMax)
        return calculateVolume(a, leftMax)
    }

    private fun fillLeftMax(a: IntArray, leftMax: IntArray) {
        var max = a[0]
        a.forEachIndexed { index, value ->
            max = maxOf(max, value)
            leftMax[index] = max
        }
    }

    private fun calculateVolume(a: IntArray, leftMax: IntArray): Int {
        var rightMax = a[a.size - 1]
        var volume = 0
        for (i in a.size - 1 downTo 0) {
            val currentValue = a[i]
            rightMax = max(rightMax, currentValue)
            val min = min(leftMax[i], rightMax)
            volume += (currentValue - min).absoluteValue
        }
        return volume
    }

}

fun main() {

    VolumeOfHistogram().apply {
        find(intArrayOf(0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 5, 0, 1, 0, 0, 0)).print()
    }

}