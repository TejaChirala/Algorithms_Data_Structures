package trees_graphs

import extensions.printNull
import stack_queues.Queue

/**
 * Build Order: You are given a list of projects and a list of dependencies (which is a list of pairs of
 * projects, where the second project is dependent on the first project). All of a project's dependencies
 * must be built before the project is. Find a build order that will allow the projects to be built. If there
 * is no valid build order, return an error.
 * EXAMPLE
 * Input:
 * projects: a, b, c, d, e, f
 * dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
 * Output: f, e, a, b, d, c
 **/
class BuildOrder {

    class Project(var name: String, var dependencies: ArrayList<Project> = ArrayList(), var inwardCount: Int = 0)

    fun getBuildOrder(projects: Array<String>, dependencies: List<Pair<String, String>>): Array<String>? {

        val projectList = HashMap<String, Project>()
        for (p in projects) {
            projectList[p] = Project(p)
        }

        for (d in dependencies) {
            projectList[d.first]?.dependencies?.add(projectList[d.second]!!)
            projectList[d.second]?.inwardCount = projectList[d.second]?.inwardCount!! + 1
        }

        var index = 0
        val buildOrder = Array(projects.size) { "" }
        val queue = Queue<Project>()

        projectList.values.filter { it.inwardCount == 0 }.forEach { queue.add(it) }

        while (!queue.isEmpty()) {

            val project = queue.remove()
            buildOrder[index] = project.name
            index++
            for (p in project.dependencies) {
                projectList[p.name]?.inwardCount = projectList[p.name]!!.inwardCount - 1
                if (projectList[p.name]?.inwardCount == 0) {
                    queue.add(p)
                }
            }
        }

        return if (index == projects.size) {
            buildOrder
        } else {
            null
        }

    }


}

fun main() {

    BuildOrder().apply {
        val result = getBuildOrder(
                arrayOf("a", "b", "c", "d", "e", "f"),
                listOf(
                        Pair("a", "d"),
                        Pair("f", "b"),
                        Pair("b", "d"),
                        Pair("f", "a"),
                        Pair("d", "c")
                )
        )
        result?.forEach {
            print("$it ")
        } ?: printNull()
    }

}