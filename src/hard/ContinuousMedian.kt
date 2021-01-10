package hard

import extensions.print
import java.util.*

/**
 * Continuous Median: Numbers are randomly generated and passed to a method. Write a program
 * to find and maintain the median value as new values are generated.
 **/
class ContinuousMedian {

    private val minHeap: Queue<Int> = PriorityQueue<Int>(10, compareBy { it })
    private val maxHeap: Queue<Int> = PriorityQueue<Int>(10, compareByDescending { it })

    fun getMedian(randomNumber: Int): Int {

        if (minHeap.size == maxHeap.size) {

            if (!maxHeap.isEmpty() && minHeap.peek() < randomNumber) {
                maxHeap.add(minHeap.poll())
                minHeap.add(randomNumber)
            } else {
                maxHeap.add(randomNumber)
            }

        } else {

            if (randomNumber < maxHeap.peek()) {
                minHeap.add(maxHeap.poll())
                maxHeap.add(randomNumber)
            } else {
                minHeap.add(randomNumber)
            }

        }

        return if (minHeap.size == maxHeap.size) (minHeap.peek() + maxHeap.peek()) / 2 else maxHeap.peek()

    }

}

fun main() {
    ContinuousMedian().apply {
        getMedian(1).print()
        getMedian(2).print()
        getMedian(3).print()
        getMedian(4).print()
        getMedian(5).print()
    }
}