package arrays_strings

/**
 *String Rotation: Assume you have a method isSubst ring which checks if one word is a substring
 * of another. Given two strings, S1 and S2, write code to check if S2 is a rotation of S1 using only one
 * call to isSubstring (e.g., "waterbottle" is a rotation of"erbottlewat").
 **/
class StringRotation {

    //Actual string will always be a substring of rotated string + rotated string.
    fun isRotation(rotatedString: String, actualString: String): Boolean {

        if (rotatedString.length != actualString.length) return false

        return isSubstring(rotatedString + rotatedString, actualString)
    }

}

private fun isSubstring(s1: String, s2: String): Boolean {
    return s1.contains(s2)
}

fun main() {
    StringRotation().apply {
        println(isRotation("erbottlewat", "waterbottle"))
        println(isRotation("cab", "abc"))
        println(isRotation("abc", "abd"))
    }
}