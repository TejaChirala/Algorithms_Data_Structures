package sorting_searching

import extensions.print

/**
 * Sparse Search: Given a sorted array of strings that is interspersed with empty strings, write a
 * method to find the location of a given string.
 * EXAMPLE
 * Input: ball, {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}
 * Output: 4
 **/
class SparseSearch {

    fun find(array: Array<String>, s: String): Int {
        return find(array, s, 0, array.size - 1)
    }

    fun find(array: Array<String>, s: String, start: Int, end: Int): Int {

        if (start > end) return -1
        var mid = (start + end) / 2
        if (array[mid].isBlank()) {

            var i = mid - 1
            var j = mid + 1

            while (i >= start && j <= end) {
                if (array[i].isBlank()) {
                    i--
                } else {
                    mid = i
                    break
                }

                if (array[j].isBlank()) {
                    j++
                } else {
                    mid = j
                    break
                }
            }
            if (array[mid].isBlank()) {
                return -1
            }

        }

        val diff = s.compareTo(array[mid])
        return when {
            diff == 0 -> mid
            diff > 0 -> {
                find(array, s, mid + 1, end)
            }
            else -> {
                find(array, s, start, mid - 1)
            }
        }


    }

}

fun main() {

    SparseSearch().apply {

        find(
                arrayOf("at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""),
                "ball"
        ).print()

        find(
                arrayOf("at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""),
                "dad"
        ).print()

        find(
                arrayOf("at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""),
                "empty"
        ).print()

    }

}