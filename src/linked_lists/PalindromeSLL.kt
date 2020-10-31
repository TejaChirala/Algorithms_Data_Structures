package linked_lists

import java.util.*

/**
 * Palindrome: Implement a function to check if a linked list is a palindrome.
 * */
class PalindromeSLL {

    private var head: Node? = null
    private var tail: Node? = null
    private var size: Int = 0

    private class Node(var data: Int, var next: Node? = null)

    /**
     * Adding the element at the first position
     * */
    fun addToFirst(value: Int) {
        val f = head
        val newNode = Node(value, head)
        head = newNode
        if (f == null) {
            tail = newNode
        }
        size++
    }

    /**
     * Adding the element at the last position
     * */
    fun addToLast(value: Int) {
        val l = tail
        val newNode = Node(value)
        tail?.next = newNode
        tail = newNode
        if (l == null) {
            head = newNode
        }
        size++
    }


    fun print() {
        var node = head
        repeat(size) {
            print("${node?.data} ")
            node = node?.next
        }
        println("")
    }

    /**
     * Iterate through the list with two pointers to determine the center of the list
     * Using a stack
     **/
    fun isPalindrome(): Boolean {
        var current = head
        var runner = head
        val stack = Stack<Int>()

        while (current != null && runner != null) {
            stack.push(current.data)
            current = current.next

            if (runner.next == null) {
                stack.pop()
            }

            runner = runner.next?.next
        }

        while (current != null) {
            if (current.data != stack.pop()) {
                return false
            }
            current = current.next
        }
        return true

    }

    fun addItems(array: Array<Int>) {
        for (i in array.indices) {
            addToLast(array[i])
        }
    }

}

fun main() {
    PalindromeSLL().apply {
        addItems(arrayOf(1, 2, 3, 2, 1))
        print()
        println(isPalindrome())
    }
    PalindromeSLL().apply {
        addItems(arrayOf(1, 2, 2, 1))
        print()
        println(isPalindrome())
    }
    PalindromeSLL().apply {
        addItems(arrayOf(1, 1))
        print()
        println(isPalindrome())
    }
    PalindromeSLL().apply {
        addItems(arrayOf(1, 2))
        print()
        println(isPalindrome())
    }
}