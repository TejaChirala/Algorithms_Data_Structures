package stack_queues

import extensions.print
import java.util.*
import kotlin.collections.ArrayList

/**
 * Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
 * Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
 * threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be
 * composed of several stacks and should create a new stack once the previous one exceeds capacity.
 * SetOfStacks.push() and SetOfStacks.pop() should behave identically to a single stack
 * (that is, pop() should return the same values as it would if there were just a single stack).
 *
 * FOLLOW UP
 * Implement a function popAt (int index) which performs a pop operation on a specific sub-stack.
 **/
class StackOfPlates(private val capacity: Int) {

    val stacks = ArrayList<Stack>()

    private fun getLastStack(): Stack {
        if (stacks.isEmpty()) {
            stacks.add(Stack())
        }
        return stacks[stacks.size - 1]
    }

    fun push(value: Int) {
        if (getLastStack().size == capacity) {
            stacks.add(Stack())
        }
        getLastStack().push(value)
    }

    fun pop(): Int {
        val value = getLastStack().pop()
        if (getLastStack().isEmpty()) {
            stacks.remove(getLastStack())
        }
        return value
    }

    fun peek(): Int {
        return getLastStack().peek()
    }

    fun isEmpty(): Boolean = stacks.isEmpty()

    fun popAt(position: Int): Int {
        val currentStack = stacks[position]
        val value = currentStack.pop()
        var index = position
        while (index + 1 < stacks.size) {
            stacks[index].push(stacks[index + 1].popBottom())
            index++
        }
        if (getLastStack().isEmpty()) {
            stacks.remove(getLastStack())
        }
        return value
    }

    class Stack {

        private data class StackNode(var data: Int, var prev: StackNode? = null, var next: StackNode? = null)

        private var _size = 0
        val size: Int
            get() = _size
        private var top: StackNode? = null
        private var bottom: StackNode? = null

        fun pop(): Int {
            if (top == null) {
                throw EmptyStackException()
            }
            val node = top
            top = top?.next
            if (top == null) {
                bottom = null
            }
            _size--
            return node!!.data
        }

        fun popBottom(): Int {
            if (top == null) {
                throw EmptyStackException()
            }
            val node = bottom
            bottom?.prev?.next = null
            bottom = bottom?.prev
            if (bottom == null) {
                top = null
            }
            _size--
            return node!!.data
        }

        fun push(t: Int) {
            val newNode = StackNode(t, null, top)
            top?.prev = newNode
            top = newNode
            if (bottom == null) {
                bottom = newNode
            }
            _size++
        }

        fun peek(): Int {
            if (top == null) {
                throw EmptyStackException()
            }
            return top!!.data
        }

        fun isEmpty(): Boolean {
            return top == null
        }

    }

}

fun main() {

    StackOfPlates(2).apply {
        push(1)
        push(2)
        push(3)
        push(4)
        push(5)
        push(6)
        stacks.size.print()
        popAt(0)
        stacks.size.print()
    }

}