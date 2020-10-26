package arrays_strings

import java.lang.StringBuilder
import kotlin.math.min

/**
 *One Away: There are three types of edits that can be performed on strings:
 * insert a character, remove a character, or replace a character.
 * Given two strings, write a function to check if they are one edit (or zero edits) away.
 * EXAMPLE
 * pale, ple -> true
 * pales. pale -> true
 * pale. bale -> true
 * pale. bake -> false
 **/
@ExperimentalStdlibApi
class OneAway {

    fun isOneAway(parent: String, child: String): Boolean {

        if (parent == child || (parent.length == 1 && child.length == 1))
            return true

        if (getDifference(parent.length, child.length) > 1)
            return false

        for (i in 0..min(parent.length - 1, child.length - 1)) {

            if (parent[i] != child[i]) {

                return when {

                    parent.length > child.length -> {
                        isInsertion(char = parent[i], index = i, parent = parent, child = child)
                    }

                    parent.length < child.length -> {
                        isDeletion(index = i, parent = parent, child = child)
                    }

                    else -> {
                        isUpdation(char = parent[i], index = i, parent = parent, child = child)
                    }
                }

            }

        }
        return true

    }

    private fun isDeletion(index: Int, parent: String, child: String): Boolean {
        return parent == removeCharAt(index, child)
    }

    private fun removeCharAt(index: Int, child: String): String {
        return StringBuilder(child).deleteAt(index).toString()
    }

    private fun isUpdation(char: Char, index: Int, parent: String, child: String): Boolean {
        return parent == updateCharAt(char, index, child)
    }

    private fun updateCharAt(char: Char, index: Int, child: String): String {
        val builder = StringBuilder(child)
        builder.setCharAt(index, char)
        return builder.toString()
    }

    private fun isInsertion(char: Char, index: Int, parent: String, child: String): Boolean {
        return parent == insertCharAt(char, index, child)
    }

    private fun insertCharAt(char: Char, index: Int, child: String): String {
        return StringBuilder(child).insert(index, char).toString()
    }

    private fun getDifference(firstValue: Int, secondValue: Int): Int {

        return if (firstValue > secondValue)
            firstValue - secondValue
        else
            secondValue - firstValue

    }

}

@ExperimentalStdlibApi
fun main() {

    OneAway().apply {
        println(isOneAway("pale", "ple"))
        println(isOneAway("pale", "bale"))
        println(isOneAway("12", "1"))
        println(isOneAway("2", "12"))
        println(isOneAway("","1"))
    }
}