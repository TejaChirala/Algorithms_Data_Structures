package hard

import kotlin.random.Random

/**
 * Shuffle: Write a method to shuffle a deck of cards. It must be a perfect shuffle-in other words, each
 * of the 52! permutations of the deck has to be equally likely. Assume that you are given a random
 * number generator which is perfect.
 **/
class Shuffle {

    private fun random(from: Int = 0, until: Int): Int {
        return Random.nextInt(from, until)
    }

    fun shuffle(cards: Array<String>) {
        for (i in cards.indices) {
            val r = random(until = i + 1)
            val temp = cards[i]
            cards[i] = cards[r]
            cards[r] = temp
        }
    }

}

fun main() {
    Shuffle().apply {
        val cards = arrayOf(
                "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
                "H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
                "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
                "S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK",
        )
        shuffle(cards)
        cards.forEach {
            print("$it ")
        }
    }
}