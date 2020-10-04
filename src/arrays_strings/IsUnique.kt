package arrays_strings

/**
 * Is Unique: Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 * */
class IsUnique {

    fun isUnique(string: String): Boolean {

        val map = HashMap<Char, Int>()
        for (char in string) {
            if (map.containsKey(char)) {
                return false
            } else {
                map[char] = 0
            }
        }
        return true

    }

    /**
     * O(n^2)
     * */
    fun isUniqueWithOutDataStructure(string: String): Boolean {

        for (i in string.indices) {
            for (j in i + 1 until string.length) {
                if (string[i] == string[j]) {
                    return false
                }
            }
        }
        return true
    }

}

fun main() {
    val unique = IsUnique()
    println(unique.isUniqueWithOutDataStructure("1234"))
    println(unique.isUnique("1"))
    println(unique.isUnique("123451"))
}
