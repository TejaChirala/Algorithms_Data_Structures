package linked_lists

fun printLine() {
    println("-------------------------------------------------")
}

fun printSLL(firstNode: SumListsSLL.Node?) {
    var node = firstNode
    while (node != null) {
        print("${node.value} ")
        node = node.next
    }
    println("")
}