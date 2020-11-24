package trees_graphs

import extensions.print
import kotlin.random.Random

/**
 * Random Node: You are implementing a binary tree class from scratch which, in addition to
 * insert, find, and delete, has a method getRandomNode() which returns a random node
 * from the tree. All nodes should be equally likely to be chosen. Design and implement an algorithm
 * for getRandomNode, and explain how you would implement the rest of the methods.
 **/
class RandomBinaryTree {

    private var root: BinaryTreeNode? = null

    data class BinaryTreeNode(var value: Int,
                              var left: BinaryTreeNode? = null,
                              var right: BinaryTreeNode? = null,
                              var children: Int = 0)

    fun insert(value: Int) {

        if (root == null) {
            root = BinaryTreeNode(value)
            return
        }

        addNode(root!!, value)
    }

    private fun addNode(currentNode: BinaryTreeNode, value: Int) {

        currentNode.children++

        if (value <= currentNode.value) {
            if (currentNode.left == null) {
                currentNode.left = BinaryTreeNode(value)
                return
            } else {
                addNode(currentNode.left!!, value)
            }
        }

        if (value > currentNode.value) {
            if (currentNode.right == null) {
                currentNode.right = BinaryTreeNode(value)
                return
            } else {
                addNode(currentNode.right!!, value)
            }
        }

    }

    fun find(value: Int): Boolean {
        return getNode(root, value)
    }

    private fun getNode(node: BinaryTreeNode?, value: Int): Boolean {
        if (node == null) return false
        if (node.value == value) return true
        return getNode(node.left, value) || getNode(node.right, value)
    }

    fun getRandomNode(): Int {
        if (root == null) {
            throw Exception("Empty tree")
        }
        val random = Random.nextInt(0, root!!.children + 1)
        return getRandomNode(root, random).value
    }

    private fun getRandomNode(node: BinaryTreeNode?, randomValue: Int): BinaryTreeNode {

        if (randomValue == getChildren(node?.left)) {
            return node!!
        }

        if (randomValue < getChildren(node?.left)) {
            return getRandomNode(node?.left, randomValue)
        }

        return getRandomNode(node?.right, randomValue - getChildren(node?.left) - 1)
    }

    private fun getChildren(node: BinaryTreeNode?) = node?.children?.plus(1) ?: 0


    fun printPreOrder() {
        preOrderTraversal(root)
    }

    private fun preOrderTraversal(node: BinaryTreeNode?) {
        node?.apply {
            print("$value -- $children \n")
            preOrderTraversal(node.left)
            preOrderTraversal(node.right)
        }
    }

}


fun main() {
    RandomBinaryTree().apply {
        insert(20)
        insert(10)
        insert(5)
        insert(40)
        insert(15)
        printPreOrder()

        find(10).print()
        find(3).print()

        getRandomNode().print()

    }
}