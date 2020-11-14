package trees_graphs

import extensions.getGraph
import extensions.getTree
import extensions.print
import linked_lists.printLine
import stack_queues.Queue

class GraphNode(var name: Any, var visited: Boolean = false, var adjacent: ArrayList<GraphNode> = ArrayList())

class Graph(var node: GraphNode)

class TreeNode(var name: Any, var left: TreeNode? = null, var right: TreeNode? = null)

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
                if (!n.visited) {
                    n.visited = true
                    queue.add(n)
                }
            }
        }
    }
}

fun main() {
    val node = getTree()
    "In order traversal".print()
    inOrderTraversal(node)
    println()
    printLine()
    "Pre order traversal".print()
    preOrderTraversal(node)
    println()
    printLine()
    "Post order traversal".print()
    postOrderTraversal(node)
    println()
    printLine()
    "Depth First Search".print()
    depthFirstSearch(getGraph())
    println()
    printLine()
    "Breadth First Search".print()
    breadthFirstSearch(getGraph())
    println()
    printLine()
}