package moderate

import extensions.print

/**
 * Number Max: Write a method that finds the maximum of two numbers. You should not use if-else
 * or any other comparison operator.
 **/
class NumberMax {

    fun getMax(a: Int, b: Int): Int {
        val c = a - b
        val sa = signOf(a)
        val sb = signOf(b)
        val sc = signOf(c)

        val signOfA = sa xor sb
        val signOfC = flip(sa xor sb)

        val k = sa * signOfA + sc * signOfC
        val v = flip(k)

        return a * k + b * v
    }

    private fun signOf(a: Int): Int {
        return if (a shr 31 and 1 == 1) 0 else 1
    }

    private fun flip(a: Int) = if (a == 1) 0 else 1

}

fun main() {
    NumberMax().apply {
        getMax(1, 5).print()
        getMax(5, 1).print()
        getMax(-5, -1).print()
        getMax(-5, 1).print()
        getMax(5, -1).print()
    }
}