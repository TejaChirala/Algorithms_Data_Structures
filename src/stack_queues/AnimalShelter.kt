package stack_queues

import extensions.print

/**
 * Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first
 * out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter,
 * or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of
 * that type). They cannot select which specific animal they would like. Create the data structures to
 * maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog,
 * and dequeueCat. You may use the built-in LinkedList data structure.
 **/
class AnimalShelter {

    private var order: Int = 0
    private val dogQueue = Queue<Dog>()
    private val catQueue = Queue<Cat>()

    abstract class Animal {
        var name: String = ""
        var order: Int = 0
    }

    class Dog : Animal()
    class Cat : Animal()

    fun enqueue(animal: Animal) {
        when (animal) {
            is Dog -> {
                animal.order = ++order
                dogQueue.add(animal)
            }
            is Cat -> {
                animal.order = ++order
                catQueue.add(animal)
            }
            else -> {
                throw Exception("Type not found")
            }
        }
    }

    fun deQueueAny(): Animal {

        return when {

            dogQueue.isEmpty() -> catQueue.remove()
            catQueue.isEmpty() -> dogQueue.remove()
            else -> {
                val dog = dogQueue.peek()
                val cat = catQueue.peek()
                return if (dog.order < cat.order) {
                    dogQueue.remove()
                } else {
                    catQueue.remove()
                }
            }
        }
    }

    fun deQueueDog(): Dog {
        return dogQueue.remove()
    }

    fun deQueueCat(): Cat {
        return catQueue.remove()
    }

}

fun main() {
    AnimalShelter().apply {
        enqueue(AnimalShelter.Dog().apply {
            name = "sasuke"
        })
        enqueue(AnimalShelter.Cat().apply {
            name = "cat"
        })
        enqueue(AnimalShelter.Dog().apply {
            name = "dog"
        })
        deQueueAny().name.print()
        deQueueCat().name.print()
        deQueueDog().name.print()
    }
}