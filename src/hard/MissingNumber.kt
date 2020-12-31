package hard

import extensions.print
import extensions.toBinaryString

/**
 * Missing Number: An array A contains all the integers from 0 to n, except for one number which
 * is missing. In this problem, we cannot access an entire integer in A with a single operation. The
 * elements of A are represented in binary, and the only operation we can use to access them is "fetch
 * the jth bit of A[i] ," which takes constant time. Write code to find the missing integer. Can you do
 * it in O(n) time?
 **/
class MissingNumber {

    private fun String.fetch(i: Int = this.length - 1) = this[i]

    fun find(a: Array<String>): Int {
        a.forEachIndexed { index, s ->
            if (index % 2 == 0 && s.fetch() != '0' || index % 2 != 0 && s.fetch() != '1') {
                return index
            }
        }
        return -1
    }

}

fun main() {
    MissingNumber().apply {
        find(
                arrayOf(
                        0.toBinaryString(),
                        1.toBinaryString(),
                        2.toBinaryString(),
                        3.toBinaryString(),
                        4.toBinaryString(),
                        5.toBinaryString(),
                        6.toBinaryString(),
                        7.toBinaryString(),
                        8.toBinaryString(),
                        10.toBinaryString(),
                        11.toBinaryString(),
                )
        ).print()

        find(
                arrayOf(
                        0.toBinaryString(),
                        1.toBinaryString(),
                        2.toBinaryString(),
                        3.toBinaryString(),
                        4.toBinaryString(),
                        5.toBinaryString(),
                        6.toBinaryString(),
                        7.toBinaryString(),
                        8.toBinaryString(),
                        9.toBinaryString(),
                        11.toBinaryString(),
                )
        ).print()
    }
}