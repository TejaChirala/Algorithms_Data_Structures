package moderate

import extensions.print


/** XML Encoding: Since XML is very verbose, you are given a way of encoding it where each tag gets
 * mapped to a pre-defined integer value. The language/grammar is as follows:
 * Element --> Tag Attributes END Children END
 * Attribute --> Tag Value
 * END --> e
 * Tag --> some predefined mapping to int
 * Value --> string value
 * For example, the following XML might be converted into the compressed string below (assuming a
 * mapping of family - > 1, person - >2, firstName - > 3, lastName - > 4, state-> 5).
 * <family lastName="McDowell" state="CA">
 *     <person firstName="Gayle">Some Message</person>
 * </family>
 * Becomes:
 * 1 4 McDowell 5 CA e 2 3 Gayle e Some Message e e
 * Write code to print the encoded version of an XML element (passed in Element and Attribute
 * objects).
 **/
class XMLParsing {

    fun parseXML(root: Element, map: HashMap<String, Int>): String {
        return parseXML(root, map, StringBuilder())
    }

    private fun parseXML(root: Element, map: HashMap<String, Int>, sb: StringBuilder): String {
        sb.appendWithSpace(map[root.tagName].toString())
        root.attributes.forEach {
            sb.appendWithSpace(map[it.first].toString())
            sb.appendWithSpace(it.second)
        }
        sb.appendWithSpace("0")

        if (root.value != null) {
            sb.appendWithSpace(root.value)
        } else {
            root.children?.forEach {
                parseXML(it, map, sb)
            }
        }
        sb.appendWithSpace("0")
        return sb.toString()
    }

    private fun StringBuilder.appendWithSpace(s: String?) {
        append(s)
        append(" ")
    }

    data class Element(
            val tagName: String,
            val value: String? = null,
            val attributes: ArrayList<Pair<String, String>>,
            val children: ArrayList<Element>? = null
    )

}

fun main() {
    val person = XMLParsing.Element(
            tagName = "person",
            attributes = arrayListOf(
                    Pair("firstName", "Teja")
            ),
            value = "I am a Developer"
    )
    val root = XMLParsing.Element(
            tagName = "family",
            attributes = arrayListOf(
                    Pair("lastName", "Chirala"),
                    Pair("state", "Kuala Lumpur")
            ),
            children = arrayListOf(person)
    )
    val map = HashMap<String, Int>().apply {
        put("family", 1)
        put("person", 2)
        put("firstName", 3)
        put("lastName", 4)
        put("state", 5)
    }

    XMLParsing().parseXML(
        root, map
    ).print()

}