package arrays_strings

/**
 *  Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palin- drome.
 *  A palindrome is a word or phrase that is the same forwards and backwards.
 *  A permutation is a rearrangement of letters.The palindrome does not need to be limited to just dictionary words.
 *  EXAMPLE
 *  Input: Tact Coa
 *  Output: True (permutations: "taco cat". "atco cta". etc.)
 * */
class PalindromePermutation {

    fun isPalindrome(string: String): Boolean {

        val map = HashMap<Char, Int>()
        for (c in string) {
            if (map.containsKey(c)) {
                map[c] = map[c]!! + 1
            } else {
                map[c] = 1
            }
        }

        var possibleSingleCharacter = false
        map.values.forEach {
            if (it % 2 == 1) {
                if (possibleSingleCharacter) {
                    return false
                } else {
                    possibleSingleCharacter = true
                }
            }
        }
        return true
    }

}

fun main() {
    PalindromePermutation().apply {
        println(isPalindrome("tactcoa"))
        println(isPalindrome("eee"))
        println(isPalindrome("etete"))
        println(isPalindrome("1"))

    }
}