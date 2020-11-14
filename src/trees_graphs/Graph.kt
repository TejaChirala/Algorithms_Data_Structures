package trees_graphs

import extensions.getGraph
import extensions.getTree
import extensions.print
import linked_lists.printLine
import stack_queues.Queue

class GraphNode<T>(var name: T, var visited: Boolean = false, var adjacent: ArrayList<GraphNode<T>> = ArrayList())

class Graph<T>(var node: GraphNode<T>)

class TreeNode<T>(var name: T, var left: TreeNode<T>? = null, var right: TreeNode<T>? = null)

class Tree<T>(var node: TreeNode<T>)

fun inOrderTraversal(node: TreeNode<Int>?) {
    node?.apply {
        inOrderTraversal(node.left)
        print("$name ")
        inOrderTraversal(node.right)
    }
}

fun preOrderTraversal(node: TreeNode<Int>?) {
    node?.apply {
        print("$name ")
        preOrderTraversal(node.left)
        preOrderTraversal(node.right)
    }
}

fun postOrderTraversal(node: TreeNode<Int>?) {
    node?.apply {
        postOrderTraversal(node.left)
        postOrderTraversal(node.right)
        print("$name ")
    }
}

fun depthFirstSearch(node: GraphNode<Int>?) {
    node?.apply {
        visited = true
        print("$name ")
        for (n in adjacent) {
            if (!n.visited) {
                depthFirstSearch(n)
            }
        }
    }
}

fun breadthFirstSearch(node: GraphNode<Int>?) {
    if (node == null)
        return

    val queue = Queue<GraphNode<Int>?>()
    queue.add(node)

    while (!queue.isEmpty()) {

        val currentNode = queue.remove()
        print("${currentNode?.name} ")

        if (currentNode?.adjacent != null) {
            for (n in currentNode.adjacent) {
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