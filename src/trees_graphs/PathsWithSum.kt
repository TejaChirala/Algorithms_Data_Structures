package trees_graphs

import extensions.print

/**
 * Paths with Sum: You are given a binary tree in which each node contains an integer value (which
 * might be positive or negative). Design an algorithm to count the number of paths that sum to a
 * given value. The path does not need to start or end at the root or a leaf, but it must go downwards
 * (traveling only from parent nodes to child nodes).
 **/
class PathsWithSum {

    fun getPathsWithSumCount(node: TreeNode<Int>?, requiredSum: Int): Int {
        if (node == null) return 0
        val map = HashMap<Int, Int>().apply { put(0, 1) }
        return findPathCount(node, map, requiredSum, 0)
    }

    private fun findPathCount(node: TreeNode<Int>?, map: HashMap<Int, Int>, targetSum: Int, sum: Int): Int {
        if (node == null) {
            return 0
        }

        val runningSum = sum + node.name
        var newPathCount = 0

        if (node.name == targetSum) {
            newPathCount++
        }
        if (map.getOrDefault(runningSum - targetSum, 0) > 0) {
            newPathCount += map[runningSum - targetSum]!!
        }
        incrementMap(map, runningSum, 1)

        newPathCount += findPathCount(node.left, map, targetSum, runningSum)
        newPathCount += findPathCount(node.right, map, targetSum, runningSum)
        incrementMap(map, runningSum, -1)
        return newPathCount

    }

    private fun incrementMap(map: HashMap<Int, Int>, key: Int, delta: Int) {
        val data = map.getOrDefault(key, 0) + delta
        if (data == 0) {
            map.remove(key)
        } else {
            map[key] = data
        }
    }

}

fun main() {
    PathsWithSum().apply {
        val n1 = TreeNode(-10, TreeNode(5), TreeNode(5))
        getPathsWithSumCount(n1, 5).print()

        val root = TreeNode(5)
        root.left = TreeNode(4)
        root.left!!.left = TreeNode(11)
        root.left!!.left!!.left = TreeNode(7)
        root.left!!.left!!.right = TreeNode(2)
        root.right = TreeNode(8)
        root.right!!.left = TreeNode(13)
        root.right!!.right = TreeNode(4)
        root.right!!.right!!.left = TreeNode(5)
        root.right!!.right!!.right = TreeNode(1)
        getPathsWithSumCount(root, 22).print()
    }
}