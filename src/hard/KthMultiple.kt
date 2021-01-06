package hard

import extensions.print
import java.util.*


/**
 * Kth Multiple: Design an algorithm to find the kth number such that the only prime factors are 3, 5,
 * and 7. Note that 3, 5, and 7 do not have to be factors, but it should not have any other prime factors.
 * For example, the first several multiples would be (in order) 1, 3, 5,7,9, 15,21.
 **/
class KthMultiple {

    fun getMultipleAt(k: Int): Int {

        val q3: Queue<Int> = LinkedList<Int>().apply { add(3) }
        val q5: Queue<Int> = LinkedList<Int>().apply { add(5) }
        val q7: Queue<Int> = LinkedList<Int>().apply { add(7) }
        var position = 1
        var result = 1
        while (position != k) {

            val pair = getMin(q3, q5, q7)
            val queue = pair.first
            val num = pair.second
            addMultiples(q3, q5, q7, num)
            result = queue.poll()
            position++

        }
        return result
    }

    private fun addMultiples(q3: Queue<Int>, q5: Queue<Int>, q7: Queue<Int>, num: Int) {
        when(num) {
            3 -> {
                q3.add(q3.peek() * num)
                q5.add(q5.peek() * num)
                q7.add(q7.peek() * num)
            }
            5 -> {
                q5.add(q5.peek() * num)
                q7.add(q7.peek() * num)
            }
            7 -> {
                q7.add(q7.peek() * num)
            }
        }
    }

    private fun getMin(q3: Queue<Int>, q5: Queue<Int>, q7: Queue<Int>): Pair<Queue<Int>, Int> {
        val a = q3.peek()
        val b = q5.peek()
        val c = q7.peek()
        return if (a < b && a< c) {
            Pair(q3, 3)
        } else if (b < a && b <c) {
            Pair(q5, 5)
        } else {
            Pair(q7, 7)
        }
    }

}

fun main() {
    KthMultiple().apply {
        getMultipleAt(1).print()
        getMultipleAt(2).print()
        getMultipleAt(9).print()
    }
}