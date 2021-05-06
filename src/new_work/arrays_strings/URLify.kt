package new_work.arrays_strings

import extensions.print

class URLify {

    fun urlify(s: String, count: Int) : String {
        val c = s.toCharArray()
        var i = s.length - 1
        var j = count - 1
        while (i != j) {
            if (c[j] == ' ') {
                c[i] = '0'
                c[i-1] = '2'
                c[i-2] = '%'
                i -= 3
                j--
            } else {
                c[i] = c[j]
                i--
                j--
            }
        }
        return String(c)
    }

}

fun main() {
    URLify().apply {
        urlify("Mr John Smith    ", 13).print()
    }
}