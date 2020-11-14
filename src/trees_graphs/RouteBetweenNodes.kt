package trees_graphs

import extensions.getGraph
import extensions.print
import stack_queues.Queue

/**
 * Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a
 * route between two nodes.
 **/
class RouteBetweenNodes {

    fun isRouteExists(source: GraphNode<Int>, destination: GraphNode<Int>): Boolean {

        val queue = Queue<GraphNode<Int>>()
        queue.add(source)
        source.visited = true

        while (!queue.isEmpty()) {
            val currentNode = queue.remove()
            for (n in currentNode.adjacent) {
                if (!n.visited) {
                    if (n.name == destination.name) {
                        return true
                    } else {
                        n.visited = true
                        queue.add(n)
                    }
                }
            }
        }
        return false
    }

}

fun main() {
    RouteBetweenNodes().apply {
        val graph = getGraph()
        isRouteExists(graph, graph.adjacent[0].adjacent[0]).print()
        isRouteExists(graph, GraphNode(10)).print()
    }
}