package trees_graphs

import extensions.getBinarySearchTree
import extensions.getTree
import extensions.print

/**
 * Validate BST: Implement a function to check if a binary tree is a binary search tree.
 **/
class ValidateBST {

    fun isBinarySearchTree(node: TreeNode<Int>?): Boolean {

        if (node == null)
            return true

        val leftNodeValue = node.left?.name
        val rightNodeValue = node.right?.name
        val isValueGreaterThanLeft = leftNodeValue == null || leftNodeValue < node.name
        val isValueLessThanRight = rightNodeValue == null || rightNodeValue > node.name
        return isValueGreaterThanLeft
                && isValueLessThanRight
                && isBinarySearchTree(node.left)
                && isBinarySearchTree(node.right)

    }

}

fun main() {
    ValidateBST().apply {
        isBinarySearchTree(getTree()).print()
        isBinarySearchTree(getBinarySearchTree()).print()
    }
}