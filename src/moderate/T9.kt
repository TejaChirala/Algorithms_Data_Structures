package moderate

import extensions.print

/**
 * T9: On old cell phones, users typed on a numeric keypad and the phone would provide a list of
 * words that matched these numbers. Each digit mapped to a set of 0 - 4 letters. Implement an algorithm
 * to return a list of matching words, given a sequence of digits. You are provided a list of valid
 * words (provided in whatever data structure you'd like). The mapping is shown in the diagram below:
 * 1 2 3
 * _ abc def
 * 4 5 6
 * ghi jkl mno
 * 7 8 9
 * pqrs tuv wxyz
 * 0
 * EXAMPLE
 * Input: 8733
 * Output: tree, used
 **/
class T9(private val numPad: Array<Array<Char>>, private val dictionary: Array<String>) {

    private val charNumMap = HashMap<Char, Int>()
    private val numStringMap = HashMap<String, ArrayList<String>>()

    init {
        mapCharToNumber()
        preProcess()
    }

    private fun mapCharToNumber() {
        numPad.forEachIndexed { index, chars ->
            chars.forEach { c ->
                charNumMap[c] = index
            }
        }
    }

    private fun preProcess() {
        val sb = StringBuilder()
        dictionary.forEach { s ->
            s.forEach { c ->
                sb.append(charNumMap[c])
            }
            addStringToMap(sb.toString(), s)
            sb.clear()
        }
    }

    private fun addStringToMap(key: String, value: String) {
        if (!numStringMap.containsKey(key)) {
            numStringMap[key] = ArrayList()
        }
        numStringMap[key]!!.add(value)
    }

    fun getValidStrings(num: String): ArrayList<String> {
        return numStringMap.getOrDefault(num, ArrayList())
    }

}

fun main() {
    val numPad = arrayOf(
            emptyArray(),
            emptyArray(), arrayOf('a', 'b', 'c'), arrayOf('d', 'e', 'f'),
            arrayOf('g', 'h', 'i'), arrayOf('j', 'k', 'l'), arrayOf('m', 'n', 'o'),
            arrayOf('p', 'q', 'r', 's'), arrayOf('t', 'u', 'v'), arrayOf('w', 'x', 'y', 'z'),
    )

    T9(numPad, arrayOf("tree", "used", "apple", "pie", "stable", "tummy", "school")).apply {
        getValidStrings("8733").print()
        getValidStrings("743").print()
    }

}