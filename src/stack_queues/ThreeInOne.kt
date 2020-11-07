package stack_queues

import extensions.print

/**
 * Three in One: Describe how you could use a single array to implement three stacks.
 **/
class ThreeInOne constructor(private val size: Int) {

    var fixedArray: IntArray = IntArray(size * 3)
    var sizeArray: IntArray = IntArray(3)

    fun push(stackIndex: Int, value: Int) {
        checkStackIndexValidity(stackIndex)
        throwExceptionIfFull(stackIndex)

        val insertPosition = (stackIndex * size) + sizeArray[stackIndex]
        fixedArray[insertPosition] = value
        sizeArray[stackIndex]++
    }

    fun pop(stackIndex: Int): Int {
        checkStackIndexValidity(stackIndex)
        throwExceptionIfEmpty(stackIndex)

        val popPosition = (stackIndex * size) + sizeArray[stackIndex] - 1
        val popValue = fixedArray[popPosition]
        fixedArray[popPosition] = 0
        sizeArray[stackIndex] = sizeArray[stackIndex]--
        return popValue
    }

    fun peek(stackIndex: Int): Int {
        checkStackIndexValidity(stackIndex)
        throwExceptionIfEmpty(stackIndex)

        val popPosition = (stackIndex * size) + sizeArray[stackIndex]
        return fixedArray[popPosition]
    }

    fun isEmpty(stackIndex: Int): Boolean {
        checkStackIndexValidity(stackIndex)
        return sizeArray[stackIndex] == 0
    }

    private fun checkStackIndexValidity(stackIndex: Int) {
        if (stackIndex >= 3 || stackIndex < 0) {
            throw Exception("Invalid index for the stack")
        }
    }

    private fun throwExceptionIfFull(stackIndex: Int) {
        if (sizeArray[stackIndex] == size) {
            throw Exception("Stack is full")
        }
    }

    private fun throwExceptionIfEmpty(stackIndex: Int) {
        if (sizeArray[stackIndex] == 0) {
            throw Exception("Stack is Empty")
        }
    }

    fun print() {
        fixedArray.forEach {
            print("$it ")
        }
        println()
    }

    fun printSize() {
        sizeArray.forEach {
            print("$it ")
        }
        println()
    }

}

fun main() {

    ThreeInOne(2).apply {
        print()
        push(0, 1)
        peek(0).print()
        push(1, 3)
        peek(1).print()
        push(2, 5)
        peek(2).print()
        print()
        pop(0).print()

    }

}