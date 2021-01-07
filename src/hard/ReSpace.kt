package hard

import extensions.print

/**
 * Re-Space: Oh, no! You have accidentally removed all spaces, punctuation, and capitalization in a
 * lengthy document. A sentence Iike "I reset the computer. It still didn't boot!"
 * became"iresetthecomputeritstilldidntboot': You'll deal with the punctuation and capitalization
 * later; right now you need to re-insert the spaces. Most of the words are in a dictionary but
 * a few are not. Given a dictionary (a list of strings) and the document (a string)' design an algorithm
 * to unconcatenate the document in a way that minimizes the number of unrecognized characters.
 * EXAMPLE:
 * Input jesslookedjustliketimherbrother
 * Output: jess looked just like tim her brother (7 unrecognized characters)
 **/
class ReSpace {

    fun addSpaces(s: String, dictionary: HashSet<String>): String {
        return split(s, dictionary, memo = arrayOfNulls(s.length)).first
    }

    private fun split(s: String, dictionary: HashSet<String>, start: Int = 0, memo: Array<Pair<String, Int>?>): Pair<String, Int> {
        if (start >= s.length) return Pair("", 0)
        if (memo[start] != null) return memo[start]!!
        var bestInvalid = Int.MAX_VALUE
        var bestParsing = ""
        var prefix = ""
        for (i in start until s.length) {
            prefix += s[i]
            val inValid = if (dictionary.contains(prefix)) 0 else prefix.length
            if (bestInvalid > inValid) {
                val pair = split(s, dictionary, i + 1, memo)
                if (inValid + pair.second < bestInvalid) {
                    bestInvalid = inValid + pair.second
                    bestParsing = prefix + " " + pair.first
                }
            }
        }
        memo[start] = Pair(bestParsing, bestInvalid)
        return memo[start]!!
    }

}

fun main() {
    ReSpace().apply {
        addSpaces("beSharp", HashSet<String>().apply {  addAll(arrayOf("Sharp"))}).print()
        addSpaces("thisisit", HashSet<String>().apply {  addAll(arrayOf("is","it","this"))}).print()
    }
}