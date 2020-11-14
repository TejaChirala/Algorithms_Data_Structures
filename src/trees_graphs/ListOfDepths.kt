package trees_graphs

import extensions.getTree
import extensions.print
import stack_queues.Queue
import java.util.*
import kotlin.collections.ArrayList

/**
 *  List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes
 *  at each depth (e.g., if you have a tree with depth 0, you'll have 0 linked lists).
 **/
class ListOfDepths {

    fun getDepthLinkedLists(rootNode: TreeNode<Int>): ArrayList<LinkedList<TreeNode<Int>>> {
        val list = ArrayList<LinkedList<TreeNode<Int>>>()
        val queue = Queue<Pair<Int, TreeNode<Int>>>()
        queue.add(Pair(0, rootNode))

        while (!queue.isEmpty()) {
            val pair = queue.remove()
            val level = pair.first
            val currentNode = pair.second

            currentNode.let {
                if (list.size <= level) {
                    list.add(LinkedList())
                }
                list[level].add(it)
            }

            currentNode.left?.let { queue.add(Pair(level + 1, it)) }
            currentNode.right?.let { queue.add(Pair(level + 1, it)) }
        }

        return list
    }

}

fun main() {
    ListOfDepths().apply {
        val depthList = getDepthLinkedLists(getTree())
        depthList.print()
    }
}