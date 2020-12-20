package moderate

import extensions.print

/**
 * Diving Board: You are building a diving board by placing a bunch of planks of wood end-to-end.
 * There are two types of planks, one of length shorter and one of length longer. You must use
 * exactly K planks of wood. Write a method to generate all possible lengths for the diving board
 **/
class DivingBoard {

    fun getPossibleLengths(short: Int, long: Int, k: Int): HashSet<Int> {

        val set = HashSet<Int>()
        if (k == 0) return set.apply { add(0) }

        for (i in 0..k) {
            val length = i * short + (k - i) * long
            set.add(length)
        }
        return set

    }

}

fun main() {

    DivingBoard().apply {
        getPossibleLengths(1, 2, 5).print()
    }

}