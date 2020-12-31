package hard

import extensions.print
import kotlin.random.Random

/**
 * Random Set: Write a method to randomly generate a set of m integers from an array of size n. Each
 * element must have equal probability of being chosen.
 **/
class RandomSet {

    fun generate(a: Array<Int>, m: Int): Array<Int>? {
        if (a.size < m) return null

        val result = Array<Int>(m) {
            a[it]
        }

        for (i in m until a.size) {
            val r = random(until = i)
            if (r < m) {
                val temp = result[r]
                result[r] = a[i]
                a[i] = temp
            }
        }
        return result
    }

    private fun random(from: Int = 0, until: Int): Int {
        return Random.nextInt(from, until)
    }

}

fun main() {
    RandomSet().apply {
        val a = arrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        generate(a, 5)?.print()
    }
}