package hard

/**
 * Multi Search: Given a string b and an array of smaller strings T, design a method to search b for
 * each small string in T.
 **/
class MultiSearch {

    fun search(b: String, T: Array<String>): HashMap<String, ArrayList<Int>> {

        val map = HashMap<String, ArrayList<Int>>()
        val trie = Trie()
        val maxLength = b.length
        T.forEach {
            if (it.length <= maxLength) {
                trie.insert(it)
            }
        }
        for (i in b.indices) {
            val strings: ArrayList<String> = getStringsAtIndex(b, trie, i)
            addStringsToMap(strings, map, i)
        }
        return map
    }

    private fun addStringsToMap(strings: ArrayList<String>, map: HashMap<String, ArrayList<Int>>, i: Int) {
        strings.forEach {
            val list = map.getOrDefault(it, ArrayList())
            list.add(i)
            map[it] = list
        }
    }

    private fun getStringsAtIndex(b: String, trie: Trie, i: Int): ArrayList<String> {
        var j = i
        val list = ArrayList<String>()
        while (j < b.length) {
            val subString = b.substring(i..j)
            val isString = trie.search(subString)
            if (isString) {
                list.add(subString)
            }
            j++
        }
        return list
    }

    class Trie {

        var root: TrieNode = TrieNode(HashMap(), false, ' ')

        fun insert(s: String) {
            root.insert(s, 0)
        }

        fun search(s: String): Boolean {
            return root.search(s, 0)
        }

    }

    data class TrieNode(
            var children: HashMap<Char, TrieNode>? = null,
            var isCompleteWord: Boolean = false,
            val data: Char) {

        fun insert(s: String, index: Int) {
            if (s.isEmpty() || index >= s.length) {
                return
            }
            if (children?.containsKey(s[index]) == true) {
                val node = children!![s[index]]!!
                node.insert(s, index + 1)
                if (index == s.length - 1) {
                    node.isCompleteWord = true
                }
            } else {
                if (children == null) {
                    children = HashMap()
                }
                val node = TrieNode(data = s[index])
                if (index == s.length - 1) {
                    node.isCompleteWord = true
                }
                children!![s[index]] = node
                node.insert(s, index + 1)
            }
        }

        fun search(s: String, index: Int): Boolean {
            if (s.isEmpty() || index >= s.length) {
                return false
            }

            if (children?.containsKey(s[index]) == true) {
                val node = children!![s[index]]!!
                return if (index == s.length - 1 && node.isCompleteWord) {
                    true
                } else {
                    node.search(s, index + 1)
                }
            }
            return false
        }

    }


}

fun main() {

    MultiSearch().apply {
        val map = search("mississippi", arrayOf("is","ppi","hi","sis", "ssippi"))
        map.forEach {
            println("${it.key} - ${it.value}")
        }
    }

}