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
class PartitionSSL {

    private var first: Node? = null
    private var last: Node? = null
    private var size: Int = 0

    private class Node(var value: Int, var next: Node? = null)

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

    fun doPartition(partitionValue: Int) {
        var current = first
        var runner = current?.next
        var i = 0
        var j = 1

        if (current == null || runner == null)
            return

        while (current != null && runner != null && i < j) {

            if (current.value < partitionValue) {
                current = current.next
                i++
            }

            if (runner.value >= partitionValue) {
                runner = runner.next
                j++
            } else {

                if (current != null) {
                    val temp = current.value
                    current.value = runner.value
                    runner.value = temp
                }

            }

        }
    }

    fun print() {
        var node = first
        repeat(size) {
            print("${node?.value} ")
            node = node?.next
        }
        println("")
    }
}

fun main() {
    PartitionSSL().apply {
        addToFirst(3)
        addToLast(5)
        addToLast(8)
        addToLast(5)
        addToLast(10)
        addToLast(2)
        addToLast(1)
        print()
        doPartition(5)
        print()
    }
    printLine()
    PartitionSSL().apply {
        addToFirst(8)
        addToLast(8)
        addToLast(8)
        addToLast(8)
        addToLast(8)
        addToLast(2)
        addToLast(1)
        print()
        doPartition(5)
        print()
    }
}