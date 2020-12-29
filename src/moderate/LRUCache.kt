package moderate

import java.lang.Exception


/**
 * LRU Cache: Design and build a "least recently used" cache, which evicts the least recently used item.
 * The cache should map from keys to values (allowing you to insert and retrieve a value associated
 * with a particular key) and be initialized with a max size. When it is full, it should evict the least
 * recently used item. You can assume the keys are integers and the values are strings.
 **/
class LRUCache<K, V>(private val maxSize: Int) {

    init {
        if (maxSize <= 0) {
            throw Exception("Max size should be above zero")
        }
    }

    private val map = HashMap<K, LinkedListNode<K, V>>()
    private var head: LinkedListNode<K, V>? = null
    private var tail: LinkedListNode<K, V>? = null
    private var size: Int = 0

    fun put(key: K, value: V) {

        if (map.containsKey(key)) {
            val node = map[key]!!
            node.data = value
            if (head != node) {
                removeNode(node)
                addNodeToStart(node)
            }
        } else {
            val node = LinkedListNode<K, V>(data = value, key = key)
            map[key] = node
            if (size == maxSize) {
                removeLast()
            }
            addNodeToStart(node)
            size++
        }

    }

    fun get(key: K): V? {
        if (!map.containsKey(key)) {
            return null
        }
        val node = map[key]!!
        if (head != node) {
            removeNode(node)
            addNodeToStart(node)
        }
        return node.data
    }

    private fun addNodeToStart(node: LinkedListNode<K, V>) {
        if (head == null) {
            head = node
            tail = node
        } else {
            node.prev = null
            head?.prev = node
            node.next = head
            head = node
        }
    }

    private fun removeNode(node: LinkedListNode<K, V>) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
    }

    private fun removeLast() {
        if (tail == null) return
        tail = tail?.prev
        tail?.next = null
        if (tail == null) head = null
    }

    data class LinkedListNode<K, V>(
            var prev: LinkedListNode<K, V>? = null,
            var next: LinkedListNode<K, V>? = null,
            var data: V,
            var key: K
    )

    fun printLL() {
        var node = head
        while (node != null) {
            print("${node.data} ")
            node = node.next
        }
        println()
    }

}

fun main() {

    LRUCache<Int, String>(3).apply {
        put(1, "apple")
        printLL()
        put(2, "ball")
        printLL()
        put(3, "cat")
        printLL()
        put(4, "doll")
        printLL()
        put(4, "dup")
        printLL()
    }
    
}