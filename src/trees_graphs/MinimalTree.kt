package trees_graphs

import extensions.print

/**
 * Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algorithm
 * to create a binary search tree with minimal height.
 **/
class MinimalTree {

    fun makeMinHeightBinarySearchTree(sortedArray: IntArray, startIndex: Int, endIndex: Int): TreeNode<Int>? {

        if (endIndex < startIndex) {
            return null
        }

        val middleIndex = (startIndex + endIndex) / 2
        val node = TreeNode(sortedArray[middleIndex])
        node.left = makeMinHeightBinarySearchTree(sortedArray, startIndex, middleIndex - 1)
        node.right = makeMinHeightBinarySearchTree(sortedArray, middleIndex + 1, endIndex)
        return node

    }
}

fun main() {
    MinimalTree().apply {
        var midNode = makeMinHeightBinarySearchTree(
                intArrayOf(1, 2, 3, 4, 5, 6),
                0,
                5
        )
        midNode.print()

        midNode = makeMinHeightBinarySearchTree(
                intArrayOf(10, 12, 23, 44, 59, 62, 79, 81, 90),
                0,
                8
        )
        midNode.print()
    }
}