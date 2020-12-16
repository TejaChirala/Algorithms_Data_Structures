package moderate

import extensions.print

/**
 * Word Frequencies: Design a method to find the frequency of occurrences of any given word in a
 * book. What if we were running this algorithm multiple times?
 **/
class WordFrequencies {

    /**
     * Running algorithm only once
     **/
    fun getFrequency(word: String, book: Array<String>): Int {
        var count = 0
        val w = word.toLowerCase().trim()

        if (w.isNotEmpty()) {
            book.forEach {
                if (w == it.trim().toLowerCase()) {
                    count++
                }
            }
        }
        return count
    }

    /**
     * Running the algorithm multiple times
     **/
    class Book(private val book: Array<String>) {

        private val map = HashMap<String, Int>()

        init {
            setFrequencies()
        }

        private fun setFrequencies() {
            book.forEach {
                val word = it.trim().toLowerCase()
                if (word.isNotEmpty()) {
                    map[word] = map.getOrDefault(word, 0) + 1
                }
            }
        }

        fun getFrequency(word: String): Int {
            val w = word.trim().toLowerCase()
            if (w.isNotEmpty()) {
                return map.getOrDefault(w, 0)
            }
            return 0
        }

    }

}

fun main() {
    WordFrequencies().apply {
        getFrequency("a", arrayOf("a ", "b", "a", "c")).print()
        getFrequency("b", arrayOf("a ", "b", "a", "c")).print()
        getFrequency("f", arrayOf("a ", "b", "a", "c")).print()
        WordFrequencies.Book(arrayOf("a ", "b", "a", "c")).let { book ->
            book.getFrequency("a").print()
            book.getFrequency("b").print()
            book.getFrequency("d").print()
        }
    }
}