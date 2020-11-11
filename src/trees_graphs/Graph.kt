package trees_graphs

import extensions.print
import linked_lists.printLine
import stack_queues.Queue

class GraphNode(var name: Any, var visited: Boolean = false, var adjacent: ArrayList<GraphNode?>?)

class Graph(var node: GraphNode)

class TreeNode(var name: Any, var left: TreeNode?, var right: TreeNode?)

class Tree(var node: TreeNode)

fun inOrderTraversal(node: TreeNode?) {
    node?.apply {
        inOrderTraversal(node.left)
        print("$name ")
        inOrderTraversal(node.right)
    }
}

fun preOrderTraversal(node: TreeNode?) {
    node?.apply {
        print("$name ")
        preOrderTraversal(node.left)
        preOrderTraversal(node.right)
    }
}

fun postOrderTraversal(node: TreeNode?) {
    node?.apply {
        postOrderTraversal(node.left)
        postOrderTraversal(node.right)
        print("$name ")
    }
}

fun depthFirstSearch(node: GraphNode?) {
    node?.apply {
        visited = true
        print("$name ")
        if (adjacent != null) {
            for (n in adjacent!!) {
                if (n?.visited == false) {
                    depthFirstSearch(n)
                }
            }
        }
    }
}

fun breadthFirstSearch(node: GraphNode?) {
    if (node == null)
        return

    val queue = Queue<GraphNode?>()
    queue.add(node)

    while (!queue.isEmpty()) {

        val currentNode = queue.remove()
        print("${currentNode?.name} ")

        if (currentNode?.adjacent != null) {
            for (n in currentNode.adjacent!!) {
                if (n?.visited == false) {
                    n.visited = true
                    queue.add(n)
                }
            }
        }
    }
}

fun main() {
    val leafNode4 = TreeNode(4, null, null)
    val leafNode5 = TreeNode(5, null, null)
    val leafNode6 = TreeNode(6, null, null)
    val leafNode7 = TreeNode(7, null, null)
    val leafNode2 = TreeNode(2, leafNode4, leafNode5)
    val leafNode3 = TreeNode(3, leafNode6, leafNode7)
    val leafNode1 = TreeNode(1, leafNode2, leafNode3)
    "In order traversal".print()
    inOrderTraversal(leafNode1)
    println()
    printLine()
    "Pre order traversal".print()
    preOrderTraversal(leafNode1)
    println()
    printLine()
    "Post order traversal".print()
    postOrderTraversal(leafNode1)
    println()
    printLine()


    val node5 = GraphNode(5, false, null)
    val node4 = GraphNode(4, false, null)
    val node3 = GraphNode(3, false, null)
    val node2 = GraphNode(2, false, null)
    val node1 = GraphNode(1, false, null)
    val node0 = GraphNode(0, false, null)

    node0.adjacent = arrayListOf(node1, node4, node5)
    node1.adjacent = arrayListOf(node3, node4)
    node2.adjacent = arrayListOf(node1)
    node3.adjacent = arrayListOf(node2, node4)

    "Depth First Search".print()
    depthFirstSearch(node0)
    println()
    printLine()

    node0.visited = false
    node1.visited = false
    node2.visited = false
    node3.visited = false
    node4.visited = false
    node5.visited = false

    "Breadth First Search".print()
    breadthFirstSearch(node0)
    println()
    printLine()
}