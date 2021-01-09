package hard

import extensions.print
import java.util.*
import kotlin.math.absoluteValue

/**
 * Shortest Supersequence: You are given two arrays, one shorter (with all distinct elements) and one
 * longer. Find the shortest subarray in the longer array that contains all the elements in the shorter
 * array. The items can appear in any order.
 * EXAMPLE
 * Input: {1, 5, 9} | {7, 5, 9, 13, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7}
 * Output: [7, 10]
 **/
class ShortestSuperSequence {

    data class HeapNode(val value: Int, val from: Int)

    fun find(small: IntArray, big: IntArray): Pair<Int, Int> {

        val map = HashMap<Int, Queue<Int>>()
        small.forEach {
            map[it] = LinkedList<Int>()
        }

        big.forEachIndexed { index, value ->
            if (map.containsKey(value)) {
                map[value]!!.add(index)
            }
        }

        val minHeap: Queue<HeapNode> = PriorityQueue<HeapNode>(small.size, compareBy { it.value })
        var max = Int.MIN_VALUE
        var min = 0
        var result = Int.MAX_VALUE
        var resultPair = Pair(-1, -1)
        map.forEach { (key, value) ->
            val v = value.poll()
            val node = HeapNode(value = v, from = key)
            minHeap.add(node)
            max = maxOf(max, v)
        }

        while (true) {
            val n = minHeap.poll()
            min = n.value

            val diff = (max - min).absoluteValue
            if (result > diff) {
                resultPair = Pair(min, max)
                result = diff
            }

            if (map[n.from]!!.isNotEmpty()) {
                val v = map[n.from]!!.poll()
                val node = HeapNode(value = v, from = n.from)
                minHeap.add(node)
                max = maxOf(max, v)
            } else {
                break
            }

        }
        return resultPair

    }

}

fun main() {
    ShortestSuperSequence().apply {
        find(intArrayOf(1, 5, 9), intArrayOf(7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7)).print()
    }
}