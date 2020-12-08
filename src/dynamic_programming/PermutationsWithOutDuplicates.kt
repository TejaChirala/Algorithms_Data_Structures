package dynamic_programming

import extensions.print

/**
 * Permutations without Dups: Write a method to compute all permutations of a string of unique
 * characters
 **/
class PermutationsWithOutDuplicates {

    /**
     * This function takes O(n!) where n is no.of characters
     * */
    fun getPermutations(s: String): ArrayList<String> {
        var list = ArrayList<String>()
        s.forEach {
            if (list.isEmpty()) {
                list.add(it.toString())
            } else {
                list = permutate(list, it)
            }
        }
        return list
    }

    private fun permutate(list: ArrayList<String>, c: Char): ArrayList<String> {
        val newList = ArrayList<String>()
        list.forEach { s ->
            for (i in 0..s.length) {
                newList.add(s.substring(0, i) + c.toString() + s.substring(i, s.length))
            }
        }
        return newList
    }

}

fun main() {
    PermutationsWithOutDuplicates().apply {
        getPermutations("abcd").print()
    }
}