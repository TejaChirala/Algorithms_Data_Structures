package stack_queues

import extensions.print

class Queue<T> {

    private data class QueueNode<T>(var data: T, var next: QueueNode<T>? = null)

    private var first: QueueNode<T>? = null
    private var last: QueueNode<T>? = null

    fun remove(): T {
        if (first == null) {
            throw NoSuchElementException()
        }
        val node = first
        if (first!!.next == null) {
            first = null
            last = null
        } else {
            first = first!!.next
        }
        return node!!.data
    }

    fun add(t: T) {
        val newNode = QueueNode(t)
        if (last != null) {
            last!!.next = newNode
        }
        last = newNode
        if (first == null) {
            first = newNode
        }
    }

    fun peek(): T {
        if (first == null) {
            throw NoSuchElementException()
        }
        return first!!.data
    }

    fun isEmpty(): Boolean {
        return first == null
    }

}

fun main() {
    Queue<Int>().apply {
        isEmpty().print()
        add(1)
        add(2)
        remove().print()
        remove().print()
        isEmpty().print()
    }
}