package sorting_searching

import extensions.print

/**
 * Rank from Stream: Imagine you are reading in a stream of integers. Periodically, you wish to be able
 * to look up the rank of a number x (the number of values less than or equal to x). Implement the data
 * structures and algorithms to support these operations. That is, implement the method track (int x),
 * which is called when each number is generated, and the method getRankOfNumber(int x),
 * which returns the number of values less than or equal to X (not including x itself).
 * EXAMPLE
 * Stream(in order of appearance): 5, 1, 4, 4, 5, 9, 7, 13, 3
 * getRankOfNumber(1) 0
 * getRankOfNumber(3) 1
 * getRankOfNumber(4) 3
 **/
class RankBinaryTree {

    private var root: RankNode? = null

    data class RankNode(var leftSize: Int = 0, var data: Int,
                        var left: RankNode? = null, var right: RankNode? = null)

    fun insert(value: Int) {
        if (root == null) {
            root = RankNode(data = value)
        } else {
            insertNode(root!!, value)
        }
    }

    private fun insertNode(node: RankNode, value: Int) {
        if (value <= node.data) {
            node.leftSize++
            if (node.left == null) {
                node.left = RankNode(data = value)
            } else {
                insertNode(node.left!!, value)
            }
        } else {
            if (node.right == null) {
                node.right = RankNode(data = value)
            } else {
                insertNode(node.right!!, value)
            }
        }
    }

    fun getRank(value: Int): Int {
        if (root == null) return -1
        return getRank(root!!, value)
    }

    private fun getRank(node: RankNode, value: Int): Int {

        return when {
            value == node.data -> {
                node.leftSize
            }
            value < node.data -> {
                if (node.left == null) return -1
                getRank(node.left!!, value)
            }
            else -> {
                if (node.right == null) return -1
                val rank = getRank(node.right!!, value)
                if (rank == -1) return -1 else (node.leftSize + rank + 1)
            }
        }
    }

}

fun main() {

    RankBinaryTree().apply {

        insert(5)
        insert(1)
        insert(4)
        insert(4)
        insert(5)
        insert(9)
        insert(7)
        insert(13)
        insert(3)

        getRank(0).print()
        getRank(1).print()
        getRank(3).print()
        getRank(4).print()
        getRank(5).print()
        getRank(7).print()
        getRank(9).print()
        getRank(13).print()

    }

}