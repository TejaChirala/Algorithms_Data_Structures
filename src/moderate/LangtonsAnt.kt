package moderate

import linked_lists.printLine
import kotlin.random.Random

/**
 * Langton's Ant: An ant is sitting on an infinite grid of white and black squares. It initially faces right.
 * At each step, it does the following:
 * (1) At a white square, flip the color of the square, turn 90 degrees right (clockwise), and move forward
 * one unit.
 * (2) At a black square, flip the color of the square, turn 90 degrees left (counter-clockwise), and move
 * forward one unit.
 * Write a program to simulate the first K moves that the ant makes and print the final board as a grid.
 * Note that you are not provided with the data structure to represent the grid. This is something you
 * must design yourself. The only input to your method is K. You should print the final grid and return
 * nothing. The method signature might be something like void printKMoves (int K) .
 **/
class LangtonsAnt {

    class Grid(private val k: Int) {

        private var rows: Int = 0
        private var columns: Int = 0
        private lateinit var grid: Array<Array<Color>>

        private val ant: Ant
        private val isWhite: Boolean

        init {
            calculateSize()
            isWhite = Random.nextBoolean()
            createGrid(isWhite)
            printGrid()
            printLine()
            ant = Ant(Direction.RIGHT, if (isWhite) Position(0, 0) else Position(rows - 1, 0))
        }

        private fun calculateSize() {
            columns = (k / 2) + 1
            rows = if (k % 2 != 0) {
                columns + 1
            } else {
                columns
            }
        }

        private fun createGrid(isWhite: Boolean): Array<Array<Color>> {
            return Array<Array<Color>>(rows) { row ->
                Array(columns) { column ->
                    if (isWhite || (!isWhite && rows % 2 == 0)) {
                        fillColors(row, column, Color.W, Color.B)
                    } else {
                        fillColors(row, column, Color.B, Color.W)
                    }
                }
            }.also { grid = it }
        }

        private fun fillColors(row: Int, column: Int, main: Color, secondary: Color) =
                if (row % 2 == 0) {
                    if (column % 2 == 0) {
                        main
                    } else {
                        secondary
                    }
                } else {
                    if (column % 2 == 0) {
                        secondary
                    } else {
                        main
                    }
                }

        private fun getColorAtPosition(position: Position): Color {
            return grid[position.row][position.column]
        }

        private fun setColorAtPosition(color: Color, position: Position) {
            grid[position.row][position.column] = color
        }

        private fun getOppositeColor(color: Color): Color {
            return if (color == Color.W) Color.B else Color.W
        }

        private fun Color.isWhite() = (this == Color.W)

        enum class Color {
            W, B
        }

        enum class Direction {
            RIGHT, UP, DOWN
        }

        data class Position(var row: Int, var column: Int) {

            fun goUp() {
                row--
            }

            fun goRight() {
                column++
            }

            fun goDown() {
                row++
            }
        }

        inner class Ant(var direction: Direction = Direction.RIGHT, val position: Position) {

            fun moveNext() {
                val currentColor = getColorAtPosition(position)
                setColorAtPosition(getOppositeColor(currentColor), position)
                when (direction) {

                    Direction.RIGHT -> {
                        if (currentColor.isWhite()) {
                            direction = Direction.DOWN
                            position.goDown()
                        } else {
                            direction = Direction.UP
                            position.goUp()
                        }
                    }
                    Direction.UP -> {
                        if (currentColor.isWhite()) {
                            direction = Direction.RIGHT
                            position.goRight()
                        }
                    }
                    Direction.DOWN -> {
                        if (!currentColor.isWhite()) {
                            direction = Direction.RIGHT
                            position.goRight()
                        }
                    }

                }
            }

        }

        fun moveAnt() {
            repeat(k) {
                ant.moveNext()
            }
        }

        fun printGrid() {
            grid.forEach {
                it.forEach {
                    print("$it ")
                }
                println()
            }
        }

    }


    fun printMoves(k: Int) {
        Grid(k).apply {
            moveAnt()
            printGrid()
        }
    }


}

fun main() {
    LangtonsAnt().apply {
        printMoves(8)
    }
}