package hard

/**
 * BiNode: Consider a simple data structure called BiNode, which has pointers to two other nodes.
 * public class BiNode {
 *      public BiNode node1, node2;
 *      public int data;
 * }
 * The data structure BiNode could be used to represent both a binary tree (where node1 is the left
 * node and node2 is the right node) or a doubly linked list (where node1 is the previous node and
 * node2 is the next node). Implement a method to convert a binary search tree (implemented with
 * BiNode) into a doubly linked list. The values should be kept in order and the operation should be
 * performed in place (that is, on the original data structure).
 **/
class BiNodeDS {

    data class BiNode(
            var node1: BiNode? = null,
            var node2: BiNode? = null,
            val data: Int
    )

    fun convertToDLL(node: BiNode?): BiNode? {
        return convert(node)?.first
    }

    private fun convert(node: BiNode?): Pair<BiNode, BiNode>? {
        if (node == null) return null
        val left = convert(node.node1)
        val right = convert(node.node2)
        return mergeNodes(left, node, right)
    }

    private fun mergeNodes(left: Pair<BiNode, BiNode>?, node: BiNode, right: Pair<BiNode, BiNode>?): Pair<BiNode, BiNode>? {

        var first: BiNode? = null
        var second: BiNode? = null
        if (left != null) {
            left.second.node2 = node
            node.node1 = left.second
            first = left.first
        } else {
            first = node
        }

        if (right != null) {
            node.node2 = right.first
            right.first.node1 = node
            second = right.second
        } else {
            second = node
        }
        return Pair(first, second)
    }

}

fun main() {
    BiNodeDS().apply {

        val n3 = BiNodeDS.BiNode(data = 3)
        val n6 = BiNodeDS.BiNode(data = 6)
        val n9 = BiNodeDS.BiNode(data = 9)
        val n2 = BiNodeDS.BiNode(null, n3, data = 2)
        val n8 = BiNodeDS.BiNode(node1 = n6, n9, data = 8)
        val n4 = BiNodeDS.BiNode(n2, n8, data = 4)


        val result = convertToDLL(n4)
        var n = result
        while (n != null) {
            print("${n.data} ")
            n = n.node2
        }

    }
}