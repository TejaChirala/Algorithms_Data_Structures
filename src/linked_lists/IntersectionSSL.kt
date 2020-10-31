package linked_lists

/**
 * Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting
 * node. Note that the intersection is defined based on reference, not value. That is, if the kth
 * node of the first linked list is the exact same node (by reference) as the jth node of the second
 * linked list, then they are intersecting.
 **/
class IntersectionSSL {

    var head: Node? = null
    private var tail: Node? = null

    class Node(var value: Int, var next: Node? = null)

    /**
     * Adding the element at the first position
     * */
    fun addToFirst(value: Int) {
        val f = head
        val newNode = Node(value, head)
        head = newNode
        if (f == null) {
            tail = newNode
        }
    }

    /**
     * Adding the element at the last position
     * */
    fun addToLast(value: Int) {
        val l = tail
        val newNode = Node(value)
        tail?.next = newNode
        tail = newNode
        if (l == null) {
            head = newNode
        }
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
    }

    fun print() {
        printSLL(head)
    }

    private fun printSLL(firstNode: Node?) {
        var node = firstNode
        while (node != null) {
            print("${node.value} ")
            node = node.next
        }
        println("")
    }

    fun addItems(array: Array<Int>) {
        for (i in array.indices) {
            addToLast(array[i])
        }
    }

    data class Result(val length: Int, val tail: Node?)

    fun getIntersection(head1: Node?, head2: Node?): Node? {

        val result1 = getResult(head1)
        val result2 = getResult(head2)

        if (result1.tail !== result2.tail) {
            return null
        }

        var node1 = head1
        var node2 = head2
        if (result1.length > result2.length) {
            repeat(result1.length - result2.length) {
                node1 = node1?.next
            }
        } else if (result1.length < result2.length) {
            repeat(result2.length - result1.length) {
                node2 = node2?.next
            }
        }

        while (node1 != null && node2 != null) {
            if (node1 === node2) {
                return node1
            }
            node1 = node1?.next
            node2 = node2?.next
        }
        return null

    }

    private fun getResult(head: Node?): Result {
        var size = 0
        var node = head
        var tail: Node? = null
        while (node != null) {
            size++
            if (node.next == null) {
                tail = node
            }
            node = node.next
        }
        return Result(size, tail)
    }

}

fun main() {
    val commonNode = IntersectionSSL.Node(3)
    val list1 = IntersectionSSL().apply {
        addItems(arrayOf(1, 2))
        addToLast(commonNode)
        addItems(arrayOf(4, 5))
        print()
    }

    val list2 = IntersectionSSL().apply {
        addItems(arrayOf(8, 9))
        addToLast(commonNode)
        print()
    }

    println(list1.getIntersection(list1.head, list2.head)?.value)

}