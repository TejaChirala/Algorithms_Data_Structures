package moderate

import extensions.print
import java.util.*

/**
 * English Int: Given any integer, print an English phrase that describes the integer (e.g., "One Thousand,
 * Two Hundred Thirty Four").
 **/
class EnglishInt {

    private val negative = "negative"
    private val hundred = "hundred"
    private val ones = arrayOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    private val tens = arrayOf("", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")
    private val hundreds = arrayOf("thousand", "million", "billion")

    fun getEnglishPhrase(num: Int): String {
        var n = num
        if (n == 0) return ones[0]
        val list = LinkedList<String>()
        if (n < 0) {
            list.add(negative)
            n *= -1
        }

        var i = 0
        while (n > 0) {

            if (n > 1000) {
                list.addAll(0, convert(n % 1000))
                list.addFirst(hundreds[i])
                i++
            } else {
                list.addAll(0, convert(n))
            }
            n /= 1000

        }

        return list.toString()

    }

    /**
     * This function converts 000 into words
     **/
    private fun convert(num: Int): LinkedList<String> {
        var n = num
        val list = LinkedList<String>()
        if (n > 100) {
            list.add(ones[n / 100])
            list.add(hundred)
            n %= 100
        }

        if (n > 10) {
            list.add(tens[n / 10])
            n %= 10
        }

        if (n > 0) {
            list.add(ones[n % 10])
        }

        return list
    }


}

fun main() {
    EnglishInt().apply {

        getEnglishPhrase(1234).print()
        getEnglishPhrase(789234).print()
        getEnglishPhrase(4789234).print()
        getEnglishPhrase(2134789234).print()

    }
}