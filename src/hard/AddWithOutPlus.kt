package hard

import extensions.print

/**
 * Add Without Plus: Write a function that adds two numbers. You should not use + or any arithmetic
 * operators.
 **/
class AddWithOutPlus {

    fun add(i: Int, j: Int): Int {
        var a = i
        var b = j
        while (b != 0) {
            val sum = a xor b
            val carry = (a and b) shl 1
            a = sum
            b = carry
        }
        return a
    }

    fun addRecursive(a: Int, b: Int): Int {
        if (b == 0) return a
        val sum = a xor b
        val carry = (a and b) shl 1
        return addRecursive(sum, carry)
    }

}

fun main() {
    AddWithOutPlus().apply {
        add(2, 3).print()
        add(12, 3).print()
        addRecursive(2, 3).print()
        addRecursive(12, 3).print()
    }
}