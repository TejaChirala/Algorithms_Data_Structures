package dynamic_programming

import extensions.print

/**
 * Parens: Implement an algorithm to print all valid (e.g., properly opened and closed) combinations
 * of n pairs of parentheses.
 * EXAMPLE
 * Input: 3
 * Output: ((())), (()()), (())(), ()(()), ()()()
 *
 **/
class Parenthesis {

    fun getCombinations(count: Int): ArrayList<String> {
        val list = ArrayList<String>()
        getCombinations(list, count, count, CharArray(count * 2), 0)
        return list
    }

    private fun getCombinations(list: ArrayList<String>,
                                leftParenCount: Int,
                                rightParenCount: Int,
                                str: CharArray,
                                index: Int) {
        when {
            leftParenCount == 0 && rightParenCount == 0 -> {
                list.add(String(str))
            }
            rightParenCount < leftParenCount -> {
                return
            }
        }
        if (leftParenCount > 0) {
            str[index] = '('
            getCombinations(list, leftParenCount - 1, rightParenCount, str, index + 1)
        }
        if (rightParenCount > 0) {
            str[index] = ')'
            getCombinations(list, leftParenCount, rightParenCount - 1, str, index + 1)
        }
    }

}

fun main() {
    Parenthesis().apply {
        getCombinations(1).print()
        getCombinations(2).print()
        getCombinations(3).print()
        getCombinations(4).print()
    }
}