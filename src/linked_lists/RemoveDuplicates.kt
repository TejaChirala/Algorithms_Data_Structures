package linked_lists

import java.util.*
import kotlin.collections.HashSet

/**
 * Write code to remove duplicates from an unsorted linked list.
 * FOLLOW UP
 * How would you solve this problem if a temporary buffer is not allowed
 **/
class RemoveDuplicates {

    fun removeDuplicates(linkedList: LinkedList<Int>): LinkedList<Int> {

        if (linkedList.size < 2) {
            return linkedList
        }
        val set = HashSet<Int>()
        val iterator = linkedList.iterator()
        iterator.forEach {
            if (set.contains(it)) {
                iterator.remove()
            } else {
                set.add(it)
            }
        }

        return linkedList

    }

}

fun LinkedList<Int>.print() {
    forEach {
        print("$it ")
    }
    println()
}

fun main() {
    RemoveDuplicates().apply {
        removeDuplicates(
            LinkedList<Int>(listOf(3, 5, 1, 4, 2, 1, 5))
        ).print()
    }
}