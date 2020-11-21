package extensions

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