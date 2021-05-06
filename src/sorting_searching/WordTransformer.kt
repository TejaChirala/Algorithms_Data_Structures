package sorting_searching

import extensions.print
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.min

class Solution {
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        var diff = Integer.MAX_VALUE
        var result = Integer.MAX_VALUE



        Arrays.sort(nums)
        for (i in nums.indices) {
            var lo = i + 1
            var hi = nums.size - 1
            while (lo < hi) {
                val sum = nums[lo] + nums[hi] + nums[i]
                val d = (sum - target).absoluteValue
                if (diff > d) {
                    diff = d
                    result = sum
                }
                if (sum < target) {
                    lo++
                } else {
                    hi--
                }
            }
        }
        return result
    }

    fun threeSumClosestOld(nums: IntArray, target: Int): Int {
        Arrays.sort(nums)
        var min = Integer.MAX_VALUE
        for (i in nums.indices) {
            val result = computeResults(nums, target, i)
            min = min(min, result)
        }
        return min
    }

    private fun computeResults(nums: IntArray, target: Int, i: Int): Int {
        var diff = Integer.MAX_VALUE
        var result = Integer.MAX_VALUE
        var lo = i + 1
        var hi = nums.size - 1
        while (lo < hi) {
            val sum = nums[lo] + nums[hi] + nums[i]
            val d = if (sum > target) sum - target else target - sum
            if (diff > d) {
                diff = d
                result = sum
            }
            if (sum < target) {
                lo++
            } else if (sum > target) {
                hi--
            } else {
                lo++
                hi--
            }
        }
        return result
    }

}

fun main() {

    print("ball, ".replace("[^a-zA-Z ]", " ")).print()
}