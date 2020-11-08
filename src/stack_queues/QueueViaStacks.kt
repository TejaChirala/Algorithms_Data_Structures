package stack_queues

import extensions.print

/**
 * Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.
 **/
class QueueViaStacks {

    private val oldStack = Stack<Int>()
    private val newStack = Stack<Int>()

    fun push(value: Int) {
        newStack.push(value)
    }

    fun pop(): Int {
        if (oldStack.isEmpty()) {
            moveNewStackToOldStack()
        }
        return oldStack.pop()
    }

    private fun moveNewStackToOldStack() {
        while (!newStack.isEmpty()) {
            oldStack.push(newStack.pop())
        }
    }

    fun peek(): Int {
        if (oldStack.isEmpty()) {
            moveNewStackToOldStack()
        }
        return oldStack.peek()
    }

    fun isEmpty(): Boolean {
        return oldStack.isEmpty() && newStack.isEmpty()
    }

}

fun main() {
    QueueViaStacks().apply {
        push(1)
        push(2)
        push(3)
        pop().print()
        pop().print()
        pop().print()
    }
}