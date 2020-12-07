package dynamic_programming

import java.util.*

/**
 * Towers of Hanoi: In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of
 * different sizes which can slide onto any tower. The puzzle starts with disks sorted in ascending order
 * of size from top to bottom (Le., each disk sits on top of an even larger one). You have the following
 * constraints:
 * (1) Only one disk can be moved at a time.
 * (2) A disk is slid off the top of one tower onto another tower.
 * (3) A disk cannot be placed on top of a smaller disk.
 * Write a program to move the disks from the first tower to the last using stacks.
 **/
class TowersOfHanoi {

    fun moveDisks(n: Int, source: Tower, dest: Tower, buffer: Tower) {
        if (n <= 0) return
        moveDisks(n - 1, source, buffer, dest)
        moveDisk(source, dest)
        moveDisks(n - 1, buffer, dest, source)
    }

    private fun moveDisk(source: Tower, dest: Tower) {
        println("Moving disks from ${source.name} - ${dest.name}")
        dest.stack.add(source.stack.pop())
    }

    data class Tower(val name: String, val stack: Stack<Int>)

}

fun main() {
    TowersOfHanoi().apply {
        moveDisks(
                3,
                TowersOfHanoi.Tower(
                        "A",
                        Stack<Int>().apply {
                            add(3)
                            add(2)
                            add(1)
                        }),
                TowersOfHanoi.Tower("C", Stack<Int>()),
                TowersOfHanoi.Tower("B", Stack<Int>()))

    }
}