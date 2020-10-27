package linked_lists

class SingleLinkedList {

    private var first: Node? = null
    private var last: Node? = null
    private var size: Int = 0

    private class Node(var value: Any?, var tail: Node? = null)

    /**
     * Adding the element at the first position
     * */
    fun addToFirst(value: Any?) {
        val f = first
        val newNode = Node(value, first)
        first = newNode
        if (f == null) {
            last = newNode
        }
        size++
    }

    /**
     * Adding the element at the last position
     * */
    fun addToLast(value: Any?) {
        val l = last
        val newNode = Node(value)
        last?.tail = newNode
        last = newNode
        if (l == null) {
            first = newNode
        }
        size++
    }

    /**
     * Removing the element at the first position
     * */
    fun removeFirst() {
        if (first == null)
            throw Exception("No element present")

        if (first == last) {
            first = null
            last = null
        } else {
            val f = first
            first = f?.tail
        }
        size--
    }

    /**
     * Removing the element at the last position
     * */
    fun removeLast() {
        if (last == null)
            throw Exception("No element present")

        if (first == last) {
            first = null
            last = null
        } else {
            val l = getNodeAt(size - 1)
            l?.tail = null
            last = l
        }
        size--
    }

    /**
     * Adding the element at the given position
     * @param position
     * */
    fun addAt(position: Int, value: Any?) {
        checkPosition(position)
        val newNode = Node(value)
        when (position) {
            0 -> {
                newNode.tail = first
                last = first
                first = newNode
            }
            size - 1 -> {
                val previousNode = getNodeAt(position - 1)
                newNode.tail = last
                previousNode?.tail = newNode
            }
            else -> {
                val previousNode = getNodeAt(position - 1)
                val nextNode = previousNode?.tail
                newNode.tail = nextNode
                previousNode?.tail = newNode
            }
        }
        size++
    }

    /**
     * Check if a position is valid
     **/
    private fun checkPosition(position: Int) {
        if (!isValidPosition(position))
            throw Exception("Not valid position")
    }

    private fun isValidPosition(position: Int): Boolean {
        return position in 0 until size
    }

    /**
     * Get value of node at specific position
     **/
    fun getValueAt(position: Int): Any? {
        return getNodeAt(position)?.value
    }

    /**
     * Get Node at specific position
     **/
    private fun getNodeAt(position: Int): Node? {
        var node = first
        repeat(position) {
            node = node?.tail
        }
        return node
    }

    fun print() {
        var node = first
        repeat(size) {
            print("${node?.value} ")
            node = node?.tail
        }
        println("")
    }

    fun printFirstLast() {
        println("${first?.value} ${first?.tail?.value} ${last?.value} ${last?.tail?.value}")
    }

    fun removeDuplicates() {
        var currentNode = first
        while (currentNode != null) {
            var runnerNode = currentNode
            while (runnerNode?.tail != null) {
                if (currentNode.value == runnerNode.tail?.value) {
                    runnerNode.tail = runnerNode.tail?.tail
                    size--
                } else {
                    runnerNode = runnerNode.tail
                }
            }
            currentNode = currentNode.tail
        }
    }

    // GET VALUE FROM LAST START

    /**
     * This function returns value based on the position from the end of the list
     **/
    fun getValueFromLast(position: Int): Any? {
        val positionFromStart = size - position
        checkPosition(positionFromStart)
        return getValueAt(positionFromStart)
    }

    fun getValueFromLastSizeUnknown(position: Int): Int {
        return getValueFromLastSizeUnknown(
                position,
                IndexNode(0, null),
                first
        ).resultNode?.value as Int? ?: throw Exception("Invalid Position")
    }

    /**
     * This function returns value based on the position from the end of the list when SIZE IS UNKNOWN
     **/
    private fun getValueFromLastSizeUnknown(position: Int, indexNode: IndexNode, currentNode: Node?): IndexNode {
        if (currentNode?.tail == null) {
            return indexNode.apply {
                index = 1
                if (position == 1) {
                    resultNode = currentNode
                }
            }
        }

        val valueNode = getValueFromLastSizeUnknown(
                position,
                indexNode,
                currentNode.tail
        ).apply { index++ }

        return if (valueNode.index == position) {
            valueNode.apply {
                resultNode = currentNode
            }
        } else {
            valueNode
        }
    }

    private data class IndexNode(var index: Int, var resultNode: Node?)

    fun getValueFromLastIteration(position: Int): Int {
        return getValueFromLastIteration(position - 1, first) ?: -1
    }

    private fun getValueFromLastIteration(position: Int, node: Node?): Int? {

        if (position < 1)
            return -1

        var current = node
        var runner = node

        //First we move the runner to {position} number of nodes
        repeat(position) {
            runner = runner?.tail
        }

        if (runner == null)
            return -1

        while (runner?.tail != null) {
            runner = runner?.tail
            current = current?.tail
        }
        return current?.value as Int?
    }

    // GET VALUE FROM LAST END

}

fun main() {
    val sll = SingleLinkedList()
    sll.apply {
        addToFirst("c")
        addAt(0, "a")
        addAt(1, "b")
        addToLast("d")
        addAt(2, "x")
        print()
    }
    println("-------------------------------")
    val duplicatesSLL = SingleLinkedList()
    duplicatesSLL.apply {
        addToFirst(1)
        addToLast(1)
        addToLast(3)
        addToLast(2)
        print()
        removeDuplicates()
        print()
        println(getValueFromLast(1))
        println(getValueFromLastSizeUnknown(1))
        println(getValueFromLastIteration(4))
    }


}