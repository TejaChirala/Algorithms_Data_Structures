package bit_manipulation

import extensions.print

/**
 * Binary to String: Given a real number between 8 and 1 (e.g., 0.72) that is passed in as a double,
 * print the binary representation. If the number cannot be represented accurately in binary with at
 * most 32 characters, print "ERROR:'
 * @see https://indepth.dev/the-simple-math-behind-decimal-binary-conversion-algorithms
 **/
class BinaryToString {

    fun convertToStringOfBinary(number: Double): String {
        var num = number
        if (num >= 1 || num <= 0) {
            return "ERROR"
        }

        val builder = StringBuilder()
        builder.append("0.")
        while (num > 0) {

            if (builder.length > 32) {
                return "ERROR"
            }

            val s = num * 2
            num = if (s >= 1) {
                builder.append(1)
                s - 1
            } else {
                builder.append(0)
                s
            }

        }
        return builder.toString()

    }

}

fun main() {
    BinaryToString().apply {
        convertToStringOfBinary(0.375).print()
        convertToStringOfBinary(0.5859375).print()
    }
}