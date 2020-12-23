package moderate

import extensions.print

/**
 * Pattern Matching: You are given two strings, pattern and value. The pattern string consists of
 * just the letters a and b, describing a pattern within a string. For example, the string catcatgocatgo
 * matches the pattern aabab (where cat is a and go is b). It also matches patterns like a, ab, and b.
 * Write a method to determine if value matches pattern.
 **/
class PatternMatching {

    fun isMatching(pattern: String, value: String): Boolean {
        val mainChar = pattern[0]
        val altChar = if (mainChar == 'a') 'b' else 'a'
        val mainCharCount = getCount(pattern, mainChar)
        val altCharCount = pattern.length - mainCharCount
        val maxMainSeqLength = value.length / mainCharCount

        for (i in 0..maxMainSeqLength) {
            val remainingSeqLength = value.length - i * mainCharCount
            val altSeqLength = if (altCharCount == 0) 0 else remainingSeqLength / altCharCount
            val altCharStartIndex = pattern.indexOf(altChar)
            val altSeqStartIndex = altCharStartIndex * i
            val mainString = value.substring(0 until i)
            val altString = if (altCharCount == 0) "" else value.substring(altSeqStartIndex until altSeqStartIndex + altSeqLength)
            val sequence = makeSequence(pattern, mainString, altString, mainChar)
            if (sequence == value) {
                return true
            }
        }
        return false
    }

    private fun makeSequence(pattern: String, mainString: String, altString: String, mainChar: Char): String {

        val sb = StringBuilder()
        pattern.forEach {
            if (it == mainChar) {
                sb.append(mainString)
            } else {
                sb.append(altString)
            }
        }
        return sb.toString()
    }

    private fun getCount(s: String, c: Char): Int {
        var count = 0
        s.forEach {
            if (c == it)
                count++
        }
        return count
    }

}

fun main() {

    PatternMatching().apply {
        isMatching(
                "aabab", "catcatgocatgo"
        ).print()

        isMatching(
                "aaa", "catcatcat"
        ).print()

        isMatching(
                "ababa", "catcatgocatgo"
        ).print()
    }

}