package trees_graphs

import extensions.print

/**
 * Successor: Write an algorithm to find the "next" node (i .e., in-order successor) of a given node in a
 * binary search tree. You may assume that each node has a link to its parent.
 **/
class Successor {

    fun getSuccessor(givenNode: ParentTreeNode<Int>): ParentTreeNode<Int>? {
        return if (givenNode.right != null) {
            leftMostNode(givenNode.right!!)
        } else {
            var q = givenNode
            var x = q.parent
            while (x != null && x.left != q) {
                q = x
                x = x.parent
            }
            x
        }
    }

    private fun leftMostNode(node: ParentTreeNode<Int>): ParentTreeNode<Int>? {
        var n = node
        while (n.left != null) {
            n = n.left!!
        }
        return n
    }

}

fun main() {

    val n10 = ParentTreeNode(10)
    val n20 = ParentTreeNode(20)
    val n30 = ParentTreeNode(30)
    val n25 = ParentTreeNode(25)
    val n23 = ParentTreeNode(23)
    val n27 = ParentTreeNode(27)
    val n26 = ParentTreeNode(26)
    val n35 = ParentTreeNode(35)

    n10.right = n20
    n20.apply {
        parent = n10
        right = n30
    }
    n30.apply {
        parent = n20
        left = n25
        right = n35
    }
    n25.apply {
        parent = n30
        left = n23
        right = n27
    }
    n23.parent = n25
    n27.apply {
        parent = n25
        left = n26
    }
    n35.parent = n30


    Successor().apply {
        getSuccessor(n25)?.name.print()
        getSuccessor(n35).print()
        n25.right = null
        getSuccessor(n25)?.name.print()
    }
}