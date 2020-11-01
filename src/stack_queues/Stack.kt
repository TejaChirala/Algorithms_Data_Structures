package stack_queues

import extensions.print
import java.util.*

class Stack<T> {

    private data class StackNode<T>(var data: T, var next: StackNode<T>? = null)

    private var top: StackNode<T>? = null

    fun pop(): T {
        if (top == null) {
            throw EmptyStackException()
        }
        val node = top
        top = top?.next
        return node!!.data
    }

    fun push(t: T) {
        val newNode = StackNode(t, top)
        top = newNode
    }

    fun peek(): T {
        if (top == null) {
            throw EmptyStackException()
        }
        return top!!.data
    }

    fun isEmpty(): Boolean {
        return top == null
    }

}

fun main() {
    Stack<Int>().apply {
        isEmpty().print()
        push(1)
        peek().print()
        pop()
        isEmpty().print()
    }
}