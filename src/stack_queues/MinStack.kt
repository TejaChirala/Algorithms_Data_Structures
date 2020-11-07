package stack_queues

import extensions.print
import java.util.*
import kotlin.math.min

/**
 * Stack Min: How would you design a stack which, in addition to push and pop, has a function min
 * which returns the minimum element? Push, pop and min should all operate in 0(1) time.
 **/
class MinStack {

    private data class StackNode(var data: Int, var min: Int, var next: StackNode? = null)

    private var top: StackNode? = null

    fun pop(): Int {
        if (top == null) {
            throw EmptyStackException()
        }
        val node = top
        top = top?.next
        return node!!.data
    }

    fun push(t: Int) {
        val min = if (isEmpty()) t else min(top!!.min, t)
        val newNode = StackNode(t, min, top)
        top = newNode
    }

    fun peek(): Int {
        if (top == null) {
            throw EmptyStackException()
        }
        return top!!.data
    }

    fun min(): Int {
        if (top == null) {
            throw EmptyStackException()
        }
        return top!!.min
    }

    fun isEmpty(): Boolean {
        return top == null
    }

}

fun main() {
    MinStack().apply {
        push(1)
        min().print()
        push(5)
        min().print()
        push(-10)
        min().print()
    }
}