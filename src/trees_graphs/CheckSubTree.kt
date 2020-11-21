package trees_graphs

import extensions.getBinarySearchTree
import extensions.getTree
import extensions.print
import sun.misc.Queue

/**
 * Check Subtree: Tl and T2 are two very large binary trees, with Tl much bigger than T2. Create an
 * algorithm to determine if T2 is a subtree of Tl.
 * A tree T2 is a subtree of T1 if there exists a node n in Tl such that the subtree of n is identical to T2 .
 * That is, if you cut off the tree at node n, the two trees would be identical.
 **/
class CheckSubTree {

    fun checkIfSubTree(mainTree: TreeNode<Int>, subTree: TreeNode<Int>): Boolean {

        val queue = Queue<TreeNode<Int>>()
        queue.enqueue(mainTree)

        while (!queue.isEmpty) {
            val currentNode = queue.dequeue()
            //Returning only when subtree matches as there can be multiple nodes with same value
            if (areEqual(currentNode, subTree) && areIdentical(currentNode, subTree)) {
                return true
            }
            currentNode?.left?.let { queue.enqueue(it) }
            currentNode?.right?.let { queue.enqueue(it) }
        }
        return false
    }

    private fun areEqual(node1: TreeNode<Int>?, node2: TreeNode<Int>?) = node1?.name == node2?.name

    private fun areIdentical(firstRoot: TreeNode<Int>?, secondRoot: TreeNode<Int>?): Boolean {

        if (firstRoot == null && secondRoot == null) {
            return true
        }

        if (firstRoot?.name != secondRoot?.name) {
            return false
        }

        return areIdentical(firstRoot?.left, secondRoot?.left) &&
                areIdentical(firstRoot?.right, secondRoot?.right)

    }

}

fun main() {

    CheckSubTree().apply {

        checkIfSubTree(getTree(), getTree()).print()
        checkIfSubTree(getTree(), getTree().right!!).print()
        checkIfSubTree(getTree(), getBinarySearchTree()).print()

    }

}