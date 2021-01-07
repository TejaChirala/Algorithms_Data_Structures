package hard

import extensions.print
import kotlin.math.absoluteValue
import kotlin.math.min

/**
 * Word Distance: You have a large text file containing words. Given any two words, find the shortest
 * distance (in terms of number of words) between them in the file. If the operation will be repeated
 * many times for the same file (but different pairs of words), can you optimize your solution?
 **/
class WordDistance {

    fun getDistance(words: Array<String>, word1: String, word2: String): Int {
        var loc1 = -1
        var loc2 = -1
        var result = Int.MAX_VALUE
        words.forEachIndexed { index, s ->
            if (s == word1) {
                loc1 = index
                result = getUpdatedDiff(loc1, loc2, result)
            } else if (s == word2) {
                loc2 = index
                result = getUpdatedDiff(loc1, loc2, result)
            }
        }
        return result
    }

    private fun getUpdatedDiff(loc1: Int, loc2: Int, result: Int): Int {
        var r = result
        if (loc1 != -1 && loc2 != -1) {
            val diff = Math.abs(loc1 - loc2)
            r = Math.min(result, diff)
        }
        return r
    }

    /**
     * For optimizing multiple requests
     **/
    class File(private val words: Array<String>) {

        val map = HashMap<String, ArrayList<Int>>()
        init {
            preProcessFile()
        }

        private fun preProcessFile() {
            words.forEachIndexed { index, s ->
                if (map.containsKey(s)) {
                    map[s]!!.add(index)
                } else {
                    map[s] = ArrayList<Int>().apply { add(index) }
                }
            }
        }

        fun getDistance(word1: String, word2: String): Int {
            val a1 = map[word1]!!
            val a2 = map[word2]!!
            var i = 0
            var j = 0
            var result = Integer.MAX_VALUE
            while (i < a1.size && j < a2.size) {
                val diff = (a1[i] - a2[j]).absoluteValue
                result = min(diff, result)
                if (a1[i] > a2[j]) {
                    j++
                } else if (a1[i] < a2[j]) {
                    i++
                } else {
                    result = 0
                    break
                }
            }
            return result
        }


    }

}

fun main() {
    WordDistance().apply {
        getDistance(
                arrayOf("x","y","a","y","c","d","a","d","a","d","b"), "a", "b"
        ).print()
        WordDistance.File(arrayOf("x","y","a","y","c","d","a","d","a","d","b")).apply {
            getDistance("a", "b").print()
        }
    }
}