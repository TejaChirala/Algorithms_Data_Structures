package moderate

import extensions.print
import linked_lists.printLine
import kotlin.math.max

/**
 * Contiguous Sequence: You are given an array of integers (both positive and negative). Find the
 * contiguous sequence with the largest sum. Return the sum.
 * EXAMPLE
 * Input 2, -8, 3, -2, 4, -10
 * Output 5 (i.e., {3, -2, 4})
 **/
class ContiguousSequence {

    fun getMaxSumOptimized(nums: Array<Int>): Int {
        var maxSum = nums[0]
        var currentSum = nums[0]
        for (i in 1 until nums.size) {
            currentSum = max(nums[i], currentSum + nums[i])
            maxSum = max(maxSum, currentSum)
        }
        return maxSum
    }

    fun getMaxSum(array: Array<Int>): Int {
        var maxSum = array[0]
        var currentSum = maxSum
        for (i in 1 until array.size) {
            currentSum = max(array[i], currentSum + array[i])
            if (maxSum >= 0 && currentSum >= 0) {
                maxSum = max(maxSum, currentSum)
            } else if ((maxSum >= 0 && currentSum < 0) || (maxSum < 0 && currentSum < maxSum)) {
                currentSum = 0
            } else {
                maxSum = max(maxSum, currentSum)
            }
        }
        return maxSum
    }


    fun getSequence(array: Array<Int>): List<Int> {

        var maxSum = array[0]
        var mStart = 0
        var mEnd = 0
        for (i in array.indices) {
            var currentSum = 0
            for (j in i until array.size) {
                currentSum += array[j]
                if (currentSum > maxSum) {
                    maxSum = currentSum
                    mStart = i
                    mEnd = j
                }

            }

        }
        return array.slice(mStart..mEnd)

    }

}

fun main() {
    ContiguousSequence().apply {
        getSequence(
                arrayOf(2, -8, 3, -2, 4, -10)
        ).print()
        getMaxSum(
                arrayOf(2, -8, 3, -2, 4, -10)
        ).print()
        getMaxSum(
                arrayOf(2, -8, 10, -9, 3, -2, 4, -10)
        ).print()
        getMaxSum(
                arrayOf(-10, -3, -2, -1, -90)
        ).print()
        getMaxSum(
                arrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
        ).print()

        printLine()
        getMaxSumOptimized(
                arrayOf(2, -8, 3, -2, 4, -10)
        ).print()
        getMaxSumOptimized(
                arrayOf(2, -8, 10, -9, 3, -2, 4, -10)
        ).print()
        getMaxSumOptimized(
                arrayOf(-10, -3, -2, -1, -90)
        ).print()
        getMaxSumOptimized(
                arrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
        ).print()
    }
}