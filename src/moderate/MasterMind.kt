package moderate

import extensions.print

/**
 * Master Mind: The Game of Master Mind is played as follows:
 * The computer has four slots, and each slot will contain a ball that is red (R), yellow (V), green (G) or
 * blue (B). For example, the computer might have RGGB (Slot #1 is red, Slots #2 and #3 are green, Slot
 * #4 is blue).
 * You, the user, are trying to guess the solution. You might, for example, guess YRGB.
 * When you guess the correct color for the correct slot, you get a "hit:' If you guess a color that exists
 * but is in the wrong slot, you get a "pseudo-hit:' Note that a slot that is a hit can never count as a
 * pseudo-hit.
 * For example, if the actual solution is RGBY and you guess GGRR, you have one hit and one pseudo-hit.
 * Write a method that, given a guess and a solution, returns the number of hits and pseudo-hits.
 **/
class MasterMind {

    fun getScore(actual: String, guess: String): Result {

        var hits = 0
        var pseudoHit = 0
        val a = IntArray(actual.length)

        //First calculating hits and storing the probable pseudo hit characters
        actual.forEachIndexed { index, c ->
            if (c == guess[index]) {
                hits++
            } else {
                a[getCode(c)] = a[getCode(c)] + 1
            }
        }

        //Checking the guess with the stored values to get the pseudo hits
        guess.forEachIndexed { index, c ->
            if (c != actual[index] && a[getCode(c)] > 0) {
                pseudoHit++
                a[getCode(c)] = a[getCode(c)] - 1
            }
        }
        return Result(hits, pseudoHit)
    }

    private fun getCode(char: Char): Int {
        return when (char) {
            'R' -> 0
            'G' -> 1
            'B' -> 2
            'Y' -> 3
            else -> throw Exception("Invalid character")
        }
    }

    data class Result(val hits: Int, val pseudoHits: Int)

}

fun main() {
    MasterMind().apply {

        getScore(
                "RGBY",
                "GGRR"
        ).print()

        getScore(
                "RGRY",
                "RGGR"
        ).print()

        getScore(
                "GRGY",
                "RBGY"
        ).print()

    }
}