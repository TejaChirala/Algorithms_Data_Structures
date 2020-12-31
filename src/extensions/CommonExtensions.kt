package extensions

import dynamic_programming.PaintFill

fun Boolean.print() {
    println(toString())
}

fun Int.print() {
    println(toString())
}

fun Double.print() {
    println(toString())
}

fun String.print() {
    println(toString())
}

fun Any?.print() {
    println(toString())
}

fun printNull() {
    println("null")
}

fun Array<Array<Int>>.print() {

    forEach { array ->
        array.forEach {
            print("$it ")
        }
        println("")
    }
    println("-----------------------------")

}

fun Array<Array<PaintFill.Color>>.print() {

    forEach { array ->
        array.forEach {
            print("$it ")
        }
        println("")
    }
    println("-----------------------------")

}

fun IntArray.print() {
    forEach {
        print("$it ")
    }
    println()
}

fun Array<Int>.print() {
    forEach {
        print("$it ")
    }
    println()
}

fun Int.toBinaryString() = Integer.toBinaryString(this)