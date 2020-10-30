package linked_lists

/**
 * Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
 * before all nodes greater than or equal to x. lf x is contained within the list, the values of x only need
 * to be after the elements less than x (see below). The partition element x can appear anywhere in the
 * "right partition"; it does not need to appear between the left and right partitions.
 * EXAMPLE
 * Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition = 5)
 * Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
 **/
class SumListsSLL {

    var first: Node? = null
    private var last: Node? = null
    private var size: Int = 0

    class Node(var value: Int, var next: Node? = null)

    /**
     * Adding the element at the first position
     * */
    fun addToFirst(value: Int) {
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
    fun addToLast(value: Int) {
        val l = last
        val newNode = Node(value)
        last?.next = newNode
        last = newNode
        if (l == null) {
            first = newNode
        }
        size++
    }

    fun print() {
        var node = first
        repeat(size) {
            print("${node?.value} ")
            node = node?.next
        }
        println("")
    }

    fun getSumOfListsReverse(list1Node: Node?, list2Node: Node?): Node? {
        var firstNode1 = list1Node
        var firstNode2 = list2Node
        var head: Node? = null
        var node: Node? = null
        var carry = 0
        while (firstNode1 != null || firstNode2 != null) {
            val sum = (firstNode1?.value ?: 0) + (firstNode2?.value ?: 0) + carry
            val nodeValue = if (sum > 9) {
                carry = sum / 10
                sum % 10
            } else {
                carry = 0
                sum
            }
            if (node == null) {
                node = Node(nodeValue, null)
                head = node
            } else {
                val newNode = Node(nodeValue, null)
                node.next = newNode
                node = newNode
            }
            firstNode1 = firstNode1?.next
            firstNode2 = firstNode2?.next
        }
        if (carry > 0) {
            val newNode = Node(carry, null)
            node?.next = newNode
        }
        return head
    }

    data class CarryNode(val carry: Int, val node: Node?)

    fun getSumOfLists(list1Node: Node?, list2Node: Node?): CarryNode? {
        val carryNode = getSumOfListsDirect(list1Node, list2Node)
        if (carryNode?.carry ?: 0 > 0) {
            val newCarryNode = getCarryNode(carryNode!!.carry)
            newCarryNode.node?.next = carryNode.node
            return newCarryNode
        }
        return carryNode
    }

    private fun getSumOfListsDirect(list1Node: Node?, list2Node: Node?): CarryNode? {

        return if (list1Node != null && list2Node != null) {
            val carryNode = getSumOfListsDirect(list1Node.next, list2Node.next)
            if (carryNode != null) {
                val sum = carryNode.carry + list1Node.value + list2Node.value
                val newCarryNode = getCarryNode(sum)
                newCarryNode.node?.next = carryNode.node
                newCarryNode
            } else {
                val sum = list1Node.value + list2Node.value
                getCarryNode(sum)
            }
        } else {
            null
        }

    }

    private fun getCarryNode(sum: Int): CarryNode {
        val carry: Int
        val nodeValue = if (sum > 9) {
            carry = sum / 10
            sum % 10
        } else {
            carry = 0
            sum
        }
        return CarryNode(carry, Node(nodeValue))
    }

}


fun main() {
    val list1 = SumListsSLL().apply {
        addToFirst(1)
        addToLast(0)
        addToLast(6)
        print()
    }
    val list2 = SumListsSLL().apply {
        addToFirst(9)
        addToLast(9)
        addToLast(1)
        print()
    }
    printLine()
    printSLL(list1.getSumOfListsReverse(list1.first, list2.first))
    printLine()

    val list3 = SumListsSLL().apply {
        addToFirst(9)
        addToLast(9)
        addToLast(9)
        print()
    }
    val list4 = SumListsSLL().apply {
        addToFirst(1)
        addToLast(1)
        addToLast(1)
        print()
    }
    printLine()
    printSLL(list1.getSumOfLists(list3.first, list4.first)?.node)

}