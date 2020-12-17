package moderate

import extensions.print

/**
 * Tic Tac Win: Design an algorithm to figure out if someone has won a game of tic-tac-toe.
 **/
class TicTacWin {

    //E is empty
    enum class CellState {
        E, X, O
    }

    //Checking for 3 X 3 matrix
    fun checkIfWon3X3(array: Array<Array<CellState>>): CellState {

        for (i in array.indices) {

            //Check Rows
            val rowCell = array[i][0]
            if (rowCell != CellState.E && rowCell == array[i][1] && rowCell == array[i][2]) {
                return rowCell
            }

            //Check Columns
            val columnCell = array[0][i]
            if (columnCell != CellState.E && columnCell == array[1][i] && columnCell == array[2][i]) {
                return rowCell
            }

        }

        //Check Diagonals
        var diagonalCell = array[0][0]
        if (diagonalCell != CellState.E && diagonalCell == array[1][1] && diagonalCell == array[2][2]) {
            return diagonalCell
        }

        diagonalCell = array[0][2]
        if (diagonalCell != CellState.E && diagonalCell == array[1][1] && diagonalCell == array[2][0]) {
            return diagonalCell
        }

        return CellState.E
    }

    //Checking for N X N matrix
    fun checkIfWonNXN(array: Array<Array<CellState>>): CellState {

        for (i in array.indices) {

            //Check Rows
            val rowCell = array[i][0]
            if (isRowComplete(array, i, rowCell)) return rowCell

            //Check Columns
            val columnCell = array[0][i]
            if (isColumnComplete(array, i, columnCell)) return columnCell

        }

        //Check Diagonals
        var diagonalCell = array[0][0]
        if (isLeftDiagonalComplete(array, diagonalCell)) {
            return diagonalCell
        }

        diagonalCell = array[0][array.size - 1]
        if (isRightDiagonalComplete(array, diagonalCell)) {
            return diagonalCell
        }

        return CellState.E
    }

    private fun isRowComplete(array: Array<Array<CellState>>, row: Int, cell: CellState): Boolean {
        if (cell == CellState.E) return false
        array[row].forEach {
            if (it != cell) return false
        }
        return true
    }

    private fun isColumnComplete(array: Array<Array<CellState>>, column: Int, cell: CellState): Boolean {
        if (cell == CellState.E) return false
        array.forEach {
            if (it[column] != cell) return false
        }
        return true
    }

    private fun isLeftDiagonalComplete(array: Array<Array<CellState>>, cell: CellState): Boolean {
        if (cell == CellState.E) return false
        for (i in 1 until array.size) {
            if (cell != array[i][i]) return false
        }
        return true
    }

    private fun isRightDiagonalComplete(array: Array<Array<CellState>>, cell: CellState): Boolean {
        if (cell == CellState.E) return false
        for (i in 1 until array.size) {
            if (cell != array[i][array.size - 1 - i]) return false
        }
        return true
    }

}

fun main() {
    TicTacWin().apply {
        val e = TicTacWin.CellState.E
        val x = TicTacWin.CellState.X
        val o = TicTacWin.CellState.O
        checkIfWon3X3(
                arrayOf(
                        arrayOf(o, o, x),
                        arrayOf(x, x, x),
                        arrayOf(o, x, o)
                )
        ).print()
        checkIfWon3X3(
                arrayOf(
                        arrayOf(o, o, x),
                        arrayOf(o, x, x),
                        arrayOf(o, x, o)
                )
        ).print()
        checkIfWon3X3(
                arrayOf(
                        arrayOf(o, o, x),
                        arrayOf(x, o, x),
                        arrayOf(o, x, o)
                )
        ).print()

        checkIfWonNXN(
                arrayOf(
                        arrayOf(o, o, x, x),
                        arrayOf(x, o, x, x),
                        arrayOf(o, x, o, o),
                        arrayOf(x, o, x, o)
                )
        ).print()

        checkIfWonNXN(
                arrayOf(
                        arrayOf(o, o, x, x),
                        arrayOf(x, o, x, x),
                        arrayOf(o, x, o, o),
                        arrayOf(o, o, x, x)
                )
        ).print()
    }
}