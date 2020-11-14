package trees_graphs

import extensions.getTree
import extensions.getUnbalancedTree
import extensions.print
import kotlin.math.absoluteValue
import kotlin.math.max

/**
 * Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of
 * this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
 * node never differ by more than one.
 **/
class CheckBalanced {

    fun isBalancedBinaryTree(node: TreeNode?): Boolean {
        return getHeight(node) != Int.MIN_VALUE
    }

    private fun getHeight(node: TreeNode?): Int {
        if (node == null) {
            return -1
        }

        val leftHeight = getHeight(node.left)
        if (leftHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE
        }
        val rightHeight = getHeight(node.right)
        if (rightHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE
        }

        val difference = (leftHeight - rightHeight).absoluteValue
        return if (difference > 1) {
            Integer.MIN_VALUE
        } else {
            max(leftHeight, rightHeight) + 1
        }
    }

}

fun main() {
    CheckBalanced().apply {
        isBalancedBinaryTree(getTree()).print()
        isBalancedBinaryTree(getUnbalancedTree()).print()
    }
}