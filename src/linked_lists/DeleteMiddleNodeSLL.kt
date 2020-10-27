package linked_lists

/**
 * Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
 * the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
 * that node.
 * EXAMPLE
 * Input: the node c from the linked list a - >b- >c - >d - >e- >f
 * Result: nothing is returned, but the new linked list looks like a - >b- >d - >e- >f
 **/
class DeleteMiddleNodeSLL {

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

    fun deleteNodeInMiddleAt(position: Int) {
        var node = first
        repeat(position) {
            node = node?.tail
        }
        deleteNodeInMiddle(node)
    }

    private fun deleteNodeInMiddle(node: Node?) {
        node?.value = node?.tail?.value
        node?.tail = node?.tail?.tail
        size--
    }

    fun print() {
        var node = first
        repeat(size) {
            print("${node?.value} ")
            node = node?.tail
        }
        println("")
    }
}

fun main() {
    DeleteMiddleNodeSLL().apply {
        addToFirst(1)
        addToLast(2)
        addToLast(3)
        addToLast(4)
        addToLast(5)
        print()
        deleteNodeInMiddleAt(3)
        print()
    }
}