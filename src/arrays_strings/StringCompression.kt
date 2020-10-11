package arrays_strings

import java.lang.StringBuilder

/**
 * String Compression: Implement a method to perform basic string compression using the counts of repeated characters.
 * For example, the string aabcccccaaa would become a2b1c5a3.
 * If the "compressed" string would not become smaller than the original string, your method should return the original string.
 * You can assume the string has only uppercase and lowercase letters (a - z).
 * */
class StringCompression {

    fun getCompressedString(string: String): String {

        val builder = StringBuilder()
        var count = 0
        for (i in string.indices) {
            count++
            if (i + 1 >= string.length || string[i] != string[i + 1]) {
                builder.append(string[i]).append(count)
                count = 0
            }
        }
        return if (string.length <= builder.length) {
            string
        } else {
            builder.toString()
        }

    }

    fun getCompressedStringOptimized(string: String): String {

        val finalCompressionLength = getCompressedStringCount(string)
        return if (finalCompressionLength < string.length) {
            val builder = StringBuilder(finalCompressionLength)
            var count = 0
            for (i in string.indices) {
                count++
                if (i + 1 >= string.length || string[i] != string[i + 1]) {
                    builder.append(string[i]).append(count)
                    count = 0
                }
            }
            return builder.toString()
        } else {
            string
        }

    }

    private fun getCompressedStringCount(string: String): Int {
        var count = 0
        var stringLength = 0
        for (i in string.indices) {
            count++
            if (i + 1 >= string.length || string[i] != string[i + 1]) {
                stringLength += 1 + count.toString().length
                count = 0
            }
        }
        return stringLength
    }

}

fun main() {
    StringCompression().apply {
        println(getCompressedString("aabbbcccc"))
        println(getCompressedStringOptimized("aabbbcccc"))
        println(getCompressedString("aabbc"))
        println(getCompressedStringOptimized("aabbc"))
    }
}