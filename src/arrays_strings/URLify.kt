package arrays_strings

import java.lang.Exception

/**
 * URLify: Write a method to replace all spaces in a string with '%20: You may assume that the string has sufficient
 * space at the end to hold the additional characters, and that you are given the "true" length of the string.
 * (Note: If implementing in Java, please use a character array so that you can perform this operation in place.)
 * */
class URLify {

    fun urlifyCreatingDuplicateArray(string: String, trueLength: Int) {

        var spaceCount = 0
        for (i in 0 until trueLength) {
            if (string[i] == ' ') {
                spaceCount++
            }
        }

        val totalCount = trueLength + spaceCount * 2
        if (totalCount > string.length) throw Exception("String length is incorrect")

        val array = string.toCharArray()
        val charArray = CharArray(array.size)
        var index = 0
        for (i in 0 until trueLength) {
            if (string[i] == ' ') {
                charArray[index] = '%'
                charArray[index + 1] = '2'
                charArray[index + 2] = '0'
                index += 3
            } else {
                charArray[index] = string[i]
                index++
            }

        }

    }

    fun urlifyUsingStringFunctions(string: String, trueLength: Int) {
        var trimmedString = string.trim()
        trimmedString = trimmedString.replace(" ","%20")
        println(trimmedString)
    }

    fun urlify(string: String, trueLength: Int) {

        var spaceCount = 0
        for (i in 0 until trueLength) {
            if (string[i] == ' ') {
                spaceCount++
            }
        }

        val totalCount = trueLength + spaceCount * 2
        if (totalCount > string.length) throw Exception("String length is incorrect")

        val charArray = string.toCharArray()
        var index = totalCount - 1
        for (i in (trueLength -1) downTo 0) {
            if (string[i] == ' ') {
                charArray[index] = '0'
                charArray[index - 1] = '2'
                charArray[index - 2] = '%'
                index -= 3
            } else {
                charArray[index] = string[i]
                index--
            }
        }

    }

}

fun main() {
    URLify().apply {
        urlify("1 2  ", 3)
        urlify("1 2 3    ", 5)
        urlifyUsingStringFunctions("1 2  ", 3)
        urlifyUsingStringFunctions("1 2 3    ", 5)
        urlify("Mr John Smith    ", 13)
    }

}