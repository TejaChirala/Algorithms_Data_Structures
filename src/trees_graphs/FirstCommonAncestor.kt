package trees_graphs

import extensions.getRightSidedTree
import extensions.getTree
import extensions.print
import extensions.printNull

class FirstCommonAncestor {

    fun getFirstCommonAncestor(root: TreeNode<Int>?,
                               p: TreeNode<Int>,
                               q: TreeNode<Int>): TreeNode<Int>? {
        val result = getFirstCommonAncestorHelper(root, p, q)
        return if (result.isAncestor) result.node else null
    }

    private fun getFirstCommonAncestorHelper(root: TreeNode<Int>?,
                                             p: TreeNode<Int>,
                                             q: TreeNode<Int>): Result {

        if (root == null) return Result(root, false)
        if (root == p && root == q) return Result(root, true)

        val leftResult = getFirstCommonAncestorHelper(root.left, p, q)
        if (leftResult.isAncestor) {
            return leftResult
        }

        val rightResult = getFirstCommonAncestorHelper(root.right, p, q)
        if (rightResult.isAncestor) {
            return rightResult
        }

        return if (leftResult.node != null && rightResult.node != null) {
            Result(root, true)
        } else if (root == p || root == q) {
            val isAncestor = leftResult.node != null || rightResult.node != null
            Result(root, isAncestor)
        } else {
            Result(leftResult.node ?: rightResult.node, false)
        }

    }

    data class Result(val node: TreeNode<Int>?, val isAncestor: Boolean)


}

fun main() {

    FirstCommonAncestor().apply {
        //Normal case
        var rootNode = getTree()
        getFirstCommonAncestor(
                root = rootNode,
                p = rootNode.left!!,
                q = rootNode.right!!.right!!)?.name?.print() ?: printNull()

        //Special case where one node is a child of other
        rootNode = getRightSidedTree()
        getFirstCommonAncestor(
                root = rootNode,
                p = rootNode.right!!,
                q = rootNode.right!!.right!!)?.name?.print() ?: printNull()

        //Special case where one node is not present
        rootNode = getRightSidedTree()
        getFirstCommonAncestor(
                root = rootNode,
                p = rootNode.right!!,
                q = TreeNode(5, null, null))?.name?.print() ?: printNull()

    }

}