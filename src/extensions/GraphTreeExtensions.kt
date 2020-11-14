package extensions

import trees_graphs.GraphNode
import trees_graphs.TreeNode

fun getGraph(): GraphNode {
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

fun getTree(): TreeNode {
    val leafNode4 = TreeNode(4, null, null)
    val leafNode5 = TreeNode(5, null, null)
    val leafNode6 = TreeNode(6, null, null)
    val leafNode7 = TreeNode(7, null, null)
    val leafNode2 = TreeNode(2, leafNode4, leafNode5)
    val leafNode3 = TreeNode(3, leafNode6, leafNode7)
    return TreeNode(1, leafNode2, leafNode3)
}