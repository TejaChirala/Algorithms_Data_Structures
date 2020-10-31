package linked_lists

/**
 * Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
 * beginning of the loop.
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
 * as to make a loop in the linked list.
 * EXAMPLE
 * Input: A -> B -> C -> 0 -> E -> C[the same C as earlier]
 * Output: C
 **/
class LoopDetectionSSL {

    var head: Node? = null
    private var tail: Node? = null
    private var size: Int = 0

    class Node(var value: Int, var next: Node? = null)

    /**
     * Adding the element at the last position
     * */
    private fun addToLast(value: Int) {
        val l = tail
        val newNode = Node(value)
        tail?.next = newNode
        tail = newNode
        if (l == null) {
            head = newNode
        }
        size++
    }

    /**
     * Adding the element at the last position
     * */
    fun addToLast(node: Node?) {
        val l = tail
        tail?.next = node
        tail = node
        if (l == null) {
            head = node
        }
        size++
    }

    fun print() {
        var node = head
        repeat(size) {
            print("${node?.value} ")
            node = node?.next
        }
        println("")
    }

    /**
     * Get Node at specific position
     **/
    fun getNodeAt(position: Int): Node? {
        var node = head
        repeat(position) {
            node = node?.next
        }
        return node
    }

    fun addItems(array: Array<Int>) {
        for (i in array.indices) {
            addToLast(array[i])
        }
    }

    fun getLoopNode(): Node? {

        var walker = head
        var runner = head

        while (runner?.next != null) {
            walker = walker?.next
            runner = runner.next?.next
            if (walker == runner) {
                break
            }
        }

        if (runner?.next == null) {
            return null
        }

        walker = head
        while (walker != runner) {
            walker = walker?.next
            runner = runner?.next
        }

        return walker
    }

}

fun main() {
    LoopDetectionSSL().apply {
        addItems(arrayOf(1, 2, 3, 4, 5))
        addToLast(LoopDetectionSSL.Node(6, getNodeAt(2)))
        printLine()
        print()
        println(getLoopNode()?.value)
        printLine()
    }

    LoopDetectionSSL().apply {
        addItems(arrayOf(1, 2, 3, 4, 5, 6))
        printLine()
        print()
        println(getLoopNode()?.value)
        printLine()
    }
}