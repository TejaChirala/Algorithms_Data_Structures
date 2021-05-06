package new_work.arrays_strings

import extensions.print

class IsUnique {

    fun isUnique(s: String): Boolean {
        val a = BooleanArray(256) {false}
        s.forEach { c ->
            if (a[c.toInt()]) {
                return false
            } else {
                a[c.toInt()] = true
            }
        }
        return true
    }

}

fun main() {
    IsUnique().apply {
        isUnique("1").print()
        isUnique("1234").print()
        isUnique("12341").print()
        isUnique("1343t").print()
    }
}