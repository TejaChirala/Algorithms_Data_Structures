package new_work.arrays_strings

import extensions.print

class PalindromePermutation {

    fun isPermPal(str: String): Boolean {
        val s = str.toLowerCase()
        val map = HashMap<Char, Int>()
        s.forEach { c ->
            if (map.containsKey(c)) {
                map[c] = map[c]!! + 1
            } else {
                map[c] = 1
            }
        }
        var flag = false
        map.values.forEach { i ->
            if (i % 2 != 0) {
                if (!flag) {
                    flag = true
                } else {
                    return false
                }
            }
        }
        return true
    }

}

fun main() {
    PalindromePermutation().apply {
        isPermPal("aaa").print()
        isPermPal("TactCoa").print()
    }
}