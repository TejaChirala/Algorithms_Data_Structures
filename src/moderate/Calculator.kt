package moderate

import extensions.print
import java.lang.Exception
import java.util.*

/**
 * Calculator: Given an arithmetic equation consisting of positive integers, +, -, * and / (no parentheses).
 * compute the result.
 * EXAMPLE
 * Input: 2*3+5/6*3+15
 * Output: 23.5
 **/
class Calculator {

    fun calculate(exp: String): Double {

        val numberStack = Stack<Double>()
        val operatorStack = Stack<Char>()

        var i = 0
        while (i < exp.length) {
            val pair = getNextNumber(i, exp)
            if (pair != null) {
                numberStack.push(pair.first.toDouble())
                i += pair.second
            }

            val operator = getNextOperator(i, exp)
            if (operator != null) {
                operate(numberStack, operatorStack, operator)
                i++
            }
        }

        while (!operatorStack.empty()) {
            val result = performOperation(numberStack.pop(), numberStack.pop(), operatorStack.pop())
            numberStack.push(result)
        }
        return numberStack.pop()
    }

    private fun operate(numberStack: Stack<Double>, operatorStack: Stack<Char>, operator: Char) {

        if (!operatorStack.empty()) {

            if (getPriority(operator) < getPriority(operatorStack.peek())) {
                val result = performOperation(numberStack.pop(), numberStack.pop(), operatorStack.pop())
                numberStack.push(result)
                operate(numberStack, operatorStack, operator)
            } else {
                operatorStack.push(operator)
            }

        } else {
            operatorStack.push(operator)
        }

    }

    private fun performOperation(b: Double, a: Double, o: Char): Double {
        return when (o) {
            '/' -> a / b
            '*' -> a * b
            '+' -> a + b
            '-' -> a - b
            else -> throw Exception("Invalid operator")
        }
    }

    private fun getNextOperator(index: Int, exp: String): Char? {
        if (index >= exp.length) return null
        val c = exp[index]
        return if (c == '/' || c == '*' || c == '+' || c == '-') return c else null
    }

    private fun getNextNumber(index: Int, exp: String): Pair<Int, Int>? {
        val sb = StringBuilder()
        var c = 0
        var i = index
        while (i < exp.length && Character.isDigit(exp[i])) {
            sb.append(exp[i])
            i++
            c++
        }
        return if (sb.isNotEmpty()) Pair(Integer.parseInt(sb.toString()), c) else null
    }

    private fun getPriority(operator: Char) = when (operator) {
        '/' -> 5
        '*' -> 4
        '+' -> 2
        '-' -> 2
        else -> throw Exception("Invalid operator")
    }

}

fun main() {
    Calculator().apply {
        calculate("2*3+5/6*3+15").print()
        calculate("6/3*5+6-15").print()
    }
}