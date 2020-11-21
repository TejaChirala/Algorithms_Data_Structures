package trees_graphs

import extensions.getBinarySearchTree
import extensions.print
import java.util.*
import kotlin.collections.ArrayList

/**
 * BST Sequences: A binary search tree was created by traversing through an array from left to right
 * and inserting each element. Given a binary search tree with distinct elements, print all possible
 * arrays that could have led to this tree.
 *
 * Input:  (1) <--- (2) ---> (3)
 * Output: {2, 1, 3}, {2, 3, 1}
 * @see https://medium.com/@jackwootton/binary-search-tree-sequences-53163b1f374a
 **/
class BSTSequence {

    fun getSequences(root: TreeNode<Int>?): ArrayList<LinkedList<Int>> {

        val result = ArrayList<LinkedList<Int>>()
        if (root == null) {
            return result
        }

        if (root.left == null && root.right == null) {
            val sequence = LinkedList<Int>()
            sequence.add(root.name)
            result.add(sequence)
            return result
        }

        val preFix = LinkedList<Int>()
        preFix.add(root.name)

        val leftSequences = getSequences(root.left)
        val rightSequences = getSequences(root.right)

        for (l in leftSequences) {
            for (r in rightSequences) {
                val weaved = ArrayList<LinkedList<Int>>()
                weave(l, r, preFix, weaved)
                result.addAll(weaved)
            }
        }
        return result
    }

    private fun weave(leftSequence: LinkedList<Int>,
                      rightSequence: LinkedList<Int>,
                      preFix: LinkedList<Int>,
                      weaved: ArrayList<LinkedList<Int>>) {

        //If leftSequence or rightSequence becomes empty return the sequence
        if (leftSequence.isEmpty() || rightSequence.isEmpty()) {
            val sequence = LinkedList<Int>()
            sequence.addAll(preFix)
            sequence.addAll(leftSequence)
            sequence.addAll(rightSequence)
            weaved.add(sequence)
            return
        }

        //Remove first of leftSequence and put at last of prefix and call the function again
        //Since we damaged the sequence we put back again
        val leftPoppedValue = leftSequence.removeFirst()
        preFix.addLast(leftPoppedValue)
        weave(leftSequence, rightSequence, preFix, weaved)
        leftSequence.addFirst(leftPoppedValue)
        preFix.removeLast()

        //Repeat the same process with rightSequence
        val rightPoppedValue = rightSequence.removeFirst()
        preFix.addLast(rightPoppedValue)
        weave(leftSequence, rightSequence, preFix, weaved)
        rightSequence.addFirst(rightPoppedValue)
        preFix.removeLast()

    }

}

fun main() {

    BSTSequence().apply {
        getSequences(getBinarySearchTree()).print()

        val n1 = TreeNode(1, null, null)
        val n3 = TreeNode(3, null, null)
        val n2 = TreeNode(2, n1, n3)

        getSequences(n2).print()
    }

}