package hard

import extensions.print

/**
 * Baby Names: Each year, the government releases a list of the 10000 most common baby names
 * and their frequencies (the number of babies with that name). The only problem with this is that
 * some names have multiple spellings. For example, "John" and "Jon" are essentially the same name
 * but would be listed separately in the list. Given two lists, one of names/frequencies and the other
 * of pairs of equivalent names, write an algorithm to print a new list of the true frequency of each
 * name. Note that if John and Jon are synonyms, and Jon and Johnny are synonyms, then John and
 * Johnny are synonyms. (It is both transitive and symmetric.) In the final list, any name can be used
 * as the "real" name.
 * EXAMPLE
 * Input:
 * Names: John (15), Jon (12), Chris (13), Kris (4), Christopher (19)
 * Synonyms: (Jon, John), (John, Johnny), (Chris, Kris), (Chris, Christopher)
 * Output: John (27), Kris (36)
 **/
class BabyNames {

    fun getList(map: HashMap<String, Int>, synonyms: ArrayList<Pair<String, String>>): ArrayList<Pair<String, Int>> {

        val tree = createTreeWithNodes(map)
        makeConnections(synonyms, tree)
        val result = ArrayList<Pair<String, Int>>()
        tree.forEach { (name, treeNode) ->
            var count = 0
            if (!treeNode.isVisited) {
                count += getCount(treeNode)
                result.add(Pair(name, count))
            }

        }
        return result
    }

    private fun getCount(node: TreeNode): Int {
        var count = 0
        if (!node.isVisited) {
            node.isVisited = true
            count += node.count
            node.children?.forEach {
                count += getCount(it)
            }
        }
        return count
    }

    private fun createTreeWithNodes(map: HashMap<String, Int>): HashMap<String, TreeNode> {
        val nodeMap = HashMap<String, TreeNode>()
        map.forEach { (name, count) ->
            val node = TreeNode(name = name, count = count)
            nodeMap[name] = node
        }
        return nodeMap
    }

    private fun makeConnections(synonyms: ArrayList<Pair<String, String>>, nodeMap: HashMap<String, TreeNode>) {
        synonyms.forEach { p ->
            val n1 = nodeMap[p.first]
            val n2 = nodeMap[p.second]
            if (n1 != null && n2 != null) {
                connectNodes(n1, n2)
                connectNodes(n2, n1)
            }
        }
    }

    private fun connectNodes(n1: TreeNode, n2: TreeNode) {
        if (n1.children != null) {
            n1.children!!.add(n2)
        } else {
            val c = ArrayList<TreeNode>()
            c.add(n2)
            n1.children = c
        }
    }

    data class TreeNode(
            var children: ArrayList<TreeNode>? = null,
            val count: Int,
            val name: String,
            var isVisited: Boolean = false
    )

}

fun main() {

    BabyNames().apply {
        val map = HashMap<String, Int>().apply {
            put("John", 15)
            put("Jon", 12)
            put("Chris", 13)
            put("Kris", 4)
            put("Christopher", 19)
        }
        val synonyms = ArrayList<Pair<String, String>>().apply {
            add(Pair("Jon", "John"))
            add(Pair("John", "Johnny"))
            add(Pair("Chris", "Kris"))
            add(Pair("Chris", "Christopher"))
        }
        getList(map, synonyms).print()
    }

}