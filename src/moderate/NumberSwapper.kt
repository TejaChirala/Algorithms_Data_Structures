package moderate

/**
 * Number Swapper: Write a function to swap a number in place (that is, without temporary variables).
 * */
class NumberSwapper {

    fun swap(i: Int, j: Int) {
        var a = i
        var b = j
        println("a - $a, b - $b")
        a += b
        b = a - b
        a -= b
        println("a - $a, b - $b")
    }

}

fun main() {
    NumberSwapper().apply {
        swap(2, 5)
        swap(-1, -2)
    }
}