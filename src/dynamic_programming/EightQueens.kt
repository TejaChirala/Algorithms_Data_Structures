package dynamic_programming

import kotlin.math.abs

class EightQueens {

    fun placeQueens(gridSize: Int): ArrayList<Array<Int>> {
        val columns = Array(gridSize) { -1 }
        val answers = ArrayList<Array<Int>>()
        placeQueens(gridSize, columns, 0, answers, gridSize)
        return answers
    }

    private fun placeQueens(gridSize: Int, columns: Array<Int>,
                            row: Int, answers: ArrayList<Array<Int>>, remaining: Int) {

        if (remaining == 0) {
            answers.add(columns.clone())
            return
        }

        for (i in 0 until gridSize) {
            if (isValidSpot(columns, row, i)) {
                columns[row] = i
                placeQueens(gridSize, columns, row + 1, answers, remaining - 1)
            }
        }
    }

    private fun isValidSpot(columns: Array<Int>, row: Int, column: Int): Boolean {

        for (r in 0 until row) {

            if (columns[r] == column) return false

            val c = columns[r]

            val rowDiff = abs(r - row)
            val columnDiff = abs(c - column)

            if (rowDiff == columnDiff) return false
        }
        return true

    }

}

fun print(a: ArrayList<Array<Int>>) {
    a.forEach {
        it.forEachIndexed { index, i ->
            print("($index, $i)")
        }
        println()
    }
}

fun main() {

    EightQueens().apply {
        print(placeQueens(1))
        print(placeQueens(1).size)
        print(placeQueens(2))
        print(placeQueens(2).size)
        print(placeQueens(3))
        print(placeQueens(3).size)
        print(placeQueens(4))
        print(placeQueens(4).size)
        print(placeQueens(8))
        print(placeQueens(8).size)
    }

}