package arrays_strings

/**
 * Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.
 * */
class CheckPermutations {

    fun checkPermutation(str1: String, str2: String): Boolean {
        if (str1.length != str2.length) {
            return false
        }

        val map1 = getUniqueCharCount(str1)
        val map2 = getUniqueCharCount(str2)

        return map1 == map2

    }

    private fun getUniqueCharCount(string: String): HashMap<Char, Int> {
        val map = HashMap<Char, Int>()
        for (c in string) {
            if (map.containsKey(c)) {
                map[c] = map[c]!! + 1
            } else {
                map[c] = 1
            }
        }
        return map
    }

}

fun main() {
    val checkPermutations = CheckPermutations()
    println(checkPermutations.checkPermutation("1232", "3221"))
    println(checkPermutations.checkPermutation("1232", "3421"))
}