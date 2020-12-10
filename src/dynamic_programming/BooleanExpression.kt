package dynamic_programming

import extensions.print

/**
 * Boolean Evaluation: Given a boolean expression consisting of the symbols
 * 0(false), 1(true), &(AND), |(OR), and ^(XOR),
 * and a desired boolean result value result,implement a function to
 * count the number of ways of parenthesizing the expression such that it evaluates to result.
 * EXAMPLE
 * countEval("1^0|0|1", false) -> 2
 * countEval("0&0&0&1^1|0", true) -> 10
 **/
class BooleanExpression {

    fun countEval(s: String, result: Boolean): Int {
        return countEval(s, result, HashMap())
    }

    private fun countEval(s: String, result: Boolean, map: HashMap<String, Int>): Int {
        if (s.isEmpty()) return 0
        if (s.length == 1) {
            return if (s.toBool() == result) 1 else 0
        }
        val key = result.toString() + s
        if (map.containsKey(key)) {
            return map[key]!!
        }

        var ways = 0
        for (i in 1 until s.length step 2) {

            val operation = s[i]

            val leftTrue = countEval(s.substring(0, i), true, map)
            val leftFalse = countEval(s.substring(0, i), false, map)
            val rightTrue = countEval(s.substring(i + 1, s.length), true, map)
            val rightFalse = countEval(s.substring(i + 1, s.length), false, map)

            val totalWays = (leftTrue + leftFalse) * (rightFalse + rightTrue)
            var successWays = 0
            when (operation) {
                '|' -> {
                    successWays = (leftTrue * rightTrue) + (leftTrue * rightFalse) + (leftFalse * rightTrue)
                }
                '^' -> {
                    successWays = (leftTrue * rightFalse) + (leftFalse * rightTrue)
                }
                '&' -> {
                    successWays = (leftTrue * rightTrue)
                }
            }
            val w = if (result) successWays else (totalWays - successWays)
            ways += w

        }
        map[key] = ways
        return ways

    }

    private fun String.toBool(): Boolean {
        return (this == "1")
    }

}

fun main() {
    BooleanExpression().apply {
        countEval("1", true).print()
        countEval("0", true).print()
        countEval("1|1", true).print()
        countEval("0^1", true).print()
        countEval("0^1|1", true).print()
        countEval("0^1|1", false).print()
    }
}