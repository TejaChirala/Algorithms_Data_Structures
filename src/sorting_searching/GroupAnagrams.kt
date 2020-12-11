package sorting_searching

import extensions.print
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Group Anagrams: Write a method to sort an array of strings so that all the anagrams are next to
 * each other.
 **/
class GroupAnagrams {

    fun sort(array: Array<String>): ArrayList<String> {

        val map = HashMap<String, ArrayList<String>>()
        array.forEach {

            val s = it.toCharArray()
            Arrays.sort(s)
            if (map.containsKey(s)) {
                map[s.toString()]!!.add(it)
            } else {
                map[s.toString()] = ArrayList<String>().apply { add(it) }
            }

        }
        val result = ArrayList<String>()
        map.values.forEach {
            result.addAll(it)
        }
        return result

    }

}

fun main() {
    GroupAnagrams().apply {
        sort(
                arrayOf(
                        "cat", "bat", "act", "tab"
                )
        ).print()
    }
}