package new_work.arrays_strings

import extensions.print

class CheckPermutation {

    fun arePerms(s1: String, s2: String): Boolean {
        if (s1.length != s2.length) return false
        val a = IntArray(256) {0}
        s1.forEach { c ->
            a[c.toInt()] = a[c.toInt()] + 1
        }
        s2.forEach { c ->
            if (a[c.toInt()] == 0) {
                return false
            }
            a[c.toInt()] = a[c.toInt()] - 1
        }
        a.forEach { i ->
            if (i != 0) return false
        }
        return true
    }

}

fun main() {
    CheckPermutation().apply {
        arePerms("12", "123").print()
        arePerms("cat", "tac").print()
    }
}