package extensions

import trees_graphs.GraphNode
import trees_graphs.TreeNode

fun getGraph(): GraphNode<Int> {
    val node5 = GraphNode(5)
    val node4 = GraphNode(4)
    val node3 = GraphNode(3)
    val node2 = GraphNode(2)
    val node1 = GraphNode(1)
    val node0 = GraphNode(0)
    node0.adjacent = arrayListOf(node1, node4, node5)
    node1.adjacent = arrayListOf(node3, node4)
    node2.adjacent = arrayListOf(node1)
    node3.adjacent = arrayListOf(node2, node4)
    return node0
}

fun getTree(): TreeNode<Int> {
    val node4 = TreeNode(4, null, null)
    val node5 = TreeNode(5, null, null)
    val node6 = TreeNode(6, null, null)
    val node7 = TreeNode(7, null, null)
    val node2 = TreeNode(2, node4, node5)
    val node3 = TreeNode(3, node6, node7)
    return TreeNode(1, node2, node3)
}

fun getUnbalancedTree(): TreeNode<Int> {
    val node4 = TreeNode(4)
    val node3 = TreeNode(3)
    val node2 = TreeNode(2, node3, node4)
    return TreeNode(1, node2, null)
}

fun getBinarySearchTree(): TreeNode<Int> {
    val node8 = TreeNode(8)
    val node5 = TreeNode(5)
    val node17 = TreeNode(17)
    val node7 = TreeNode(7, node5, node8)
    return TreeNode(10, node7, node17)
}