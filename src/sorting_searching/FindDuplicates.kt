package sorting_searching

/**
 * Find Duplicates: You have an array with all the numbers from 1 to N, where N is at most 32,000. The
 * array may have duplicate entries and you do not know what N is. With only 4 kilobytes of memory
 * available, how would you print all duplicate elements in the array?
 **/
class FindDuplicates {

    fun printDups(array: IntArray) {

        val bits = BitSet(32000)
        array.forEach {
            if (bits.get(it)) {
                print("$it ")
            } else {
                bits.set(it)
            }
        }
        println()

    }

    class BitSet(size: Int) {
        val array: IntArray = IntArray((size shr 5) + 1)

        fun get(pos: Int): Boolean {
            val intPos = pos shr 5
            val bitPos = pos % 32
            return (array[intPos] and (1 shl bitPos)) != 0
        }

        fun set(pos: Int) {
            val intPos = pos shr 5
            val bitPos = pos % 32
            array[intPos] = (array[intPos] or (1 shl bitPos))
        }

    }

}

fun main() {
    FindDuplicates().apply {
        printDups(intArrayOf(1, 2, 3, 4, 4, 5, 6, 6))
    }
}