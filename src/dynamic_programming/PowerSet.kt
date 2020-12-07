package dynamic_programming

import extensions.print

/**
 * Power Set: Write a method to return all subsets of a set.
 **/
class PowerSet {

    fun getPowerSet(set: Set<Int>): ArrayList<ArrayList<Int>> {
        val powerSet = ArrayList<ArrayList<Int>>().apply { add(arrayListOf()) }
        set.forEach { value ->
            val copy = ArrayList<ArrayList<Int>>()
            powerSet.forEach {
                copy.add(it.clone() as ArrayList<Int>)
            }
            copy.forEach { it ->
                it.add(value)
            }
            powerSet.addAll(copy)
        }
        return powerSet
    }

}

fun main() {
    PowerSet().apply {
        getPowerSet(setOf(1, 2, 3)).print()
    }
}