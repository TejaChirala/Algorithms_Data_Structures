package dynamic_programming

import extensions.print

/**
 * Permutations with Dups: Write a method to compute all permutations of a string whose characters
 * are not necessarily unique. The list of permutations should not have duplicates.
 **/
class PermutationsWithDuplicates {

    /**
     * This function takes O(n!) where n is no.of characters
     * */
    fun getPermutations(s: String): ArrayList<String> {
        val list = ArrayList<String>()
        getPermutations(s.toMap(), list, "", s.length)
        return list
    }

    private fun String.toMap(): HashMap<Char, Int> {
        val map = HashMap<Char, Int>()
        this.forEach {
            if (map.containsKey(it)) {
                map[it] = map[it]!! + 1
            } else {
                map[it] = 1
            }
        }
        return map
    }

    private fun getPermutations(map: HashMap<Char, Int>, list: ArrayList<String>, prefix: String = "", remaining: Int) {
        if (remaining == 0) {
            list.add(prefix)
            return
        }
        map.keys.forEach {
            if (map[it]!! > 0) {
                val p = prefix + it.toString()
                map[it] = map[it]!! - 1
                getPermutations(map, list, p, remaining - 1)
                map[it] = map[it]!! + 1
            }
        }
    }

}

fun main() {
    PermutationsWithDuplicates().apply {
        getPermutations("aab").print()
        getPermutations("abbccc").print()
    }
}