package stack_queues

/**
 * Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use
 * an additional temporary stack, but you may not copy the elements into any other data structure
 * (such as an array). The stack supports the following operations: push, pop, peek, and isEmpty.
 **/
class SortStack {

    private val tempStack = Stack<Int>()
    private val sortedStack = Stack<Int>()

    fun push(value: Int) {
        putValueInSortedStack(value)
        moveTemptStackToSortedStack()
    }

    private fun putValueInSortedStack(value: Int) {
        when {
            sortedStack.isEmpty() -> {
                sortedStack.push(value)
            }
            sortedStack.peek() < value -> {
                tempStack.push(sortedStack.pop())
                putValueInSortedStack(value)
            }
            else -> {
                sortedStack.push(value)
            }
        }
    }

    private fun moveTemptStackToSortedStack() {
        while (!tempStack.isEmpty()) {
            sortedStack.push(tempStack.pop())
        }
    }

    fun pop(): Int = sortedStack.pop()

    fun peek(): Int = sortedStack.peek()

    fun isEmpty(): Boolean = sortedStack.isEmpty()

}

fun main() {

    SortStack().apply {
        push(1)
        push(2)
        push(10)
        push(5)
        push(7)
        peek()
    }

}