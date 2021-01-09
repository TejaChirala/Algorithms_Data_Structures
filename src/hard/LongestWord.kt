package hard

import extensions.print

/**
 * Longest Word: Given a list of words, write a program to find the longest word made of other words
 * in the list.
 * EXAMPLE
 * Input cat, banana, dog, nana, walk, walker, dogwalker
 * Output: dogwalker
 **/
class LongestWord {

    fun find(a: Array<String>): String {
        a.sortBy { it.length }
        val map = HashMap<String, Boolean>()
        a.forEach {
            map[it] = true
        }

        for (i in a.size - 1 downTo 0) {

            if (map[a[i]]!! && isMadeOfWords(map, a[i], true)) {
                return a[i]
            }

        }
        return ""
    }

    private fun isMadeOfWords(map: java.util.HashMap<String, Boolean>, s: String, isOriginalWord: Boolean): Boolean {

        if (map.containsKey(s) && !isOriginalWord) {
            return map[s]!!
        }

        for (i in s.indices) {
            val left = s.substring(0, i)
            val right = s.substring(i)
            if (map.getOrDefault(left, false) && isMadeOfWords(map, right, false)) {
                return true
            }
        }
        map[s] = false
        return false
    }

}

fun main() {
    LongestWord().apply {
        find(arrayOf("cat", "banana", "dog", "nana", "walk", "walker", "dogwalker")).print()
    }
}