package ru.se.itmo.prog.lab6.commands

import ru.itmo.se.prog.lab6.data.Coordinates
import ru.itmo.se.prog.lab6.data.Location
import ru.se.itmo.prog.lab6.data.Person
import org.jetbrains.kotlin.konan.file.File
import ru.itmo.se.prog.lab6.utils.AddPersonFields
import ru.itmo.se.prog.lab6.utils.PrinterManager
import java.util.*

/**
 * Updates the collection item with the entered identifier.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Update: Command() {
    private val set = AddPersonFields()
    private val writeToConsole = PrinterManager()
    override fun getName(): String {
        return "update"
    }

    override fun getDescription(): String {
        return getName() + " - обновляет элемент коллекции по указанному id\n"
    }

    override fun execute(args: Map<String, Any?>): String? {
        var result: String? = ""
        val elementId = if (args.contains("elementId")) args["elementId"] as Int else 0
        val path = if (args.contains("path")) args["path"] as String else ""
        val flag = if (args.contains("flag")) args["flag"] as String else ""
        val id: Int
        var element: Person? = null
        val tmpMap = mutableMapOf<String, Any>()
        var params = arrayListOf("null parameter", "null parameter", "null parameter", "null parameter", "null parameter",
                                         "null parameter", "null parameter", "null parameter", "null parameter", "null parameter")

        tmpMap["path"] = path
        tmpMap["elementId"] = elementId

        if (flag != "main") {
            params = parametersParser(tmpMap)
        }

        try {
            for (obj in collectionManager.collection) {
                if (obj.id == args["elementId"]) {
                    element = obj
                    break
                }
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
            result = message.getMessage("invalid_id")
            return result
        }

        try {
            id = element?.id as Int
        } catch (e: NullPointerException) {
            result = message.getMessage("invalid_id")
            return result
        }

        val name = set.name(params[0], flag)

        val coordinates = Coordinates(set.coordinateX(params[1], flag), set.coordinateY(params[2], flag))

        val creationDate = Date()

        val height = set.height(params[3], flag)

        val weight = set.weight(params[4], flag)

        val hairColor = set.hairColor(params[5], flag)

        val nationality = set.nationality(params[6], flag)

        val location = Location(set.locationX(params[7], flag), set.locationY(params[8], flag), set.locationZ(params[9], flag))

        element = Person(id, name, coordinates, creationDate, height, weight, hairColor, nationality, location)

        val bufferCollection = mutableListOf<Person>()
        for (el in collectionManager.collection) {
            if (el.id != element.id) {
                bufferCollection.add(el)
            } else {
                bufferCollection.add(element)
            }
        }
        collectionManager.collection = bufferCollection
        result = message.getMessage("updated")
        return result
    }

    /**
     * Parses item parameters from the script file.
     *
     * @author svinoczar
     * @since 1.0.0
     */
    private fun parametersParser(args: Map<String, Any>): ArrayList<String> {
        val path: String by args
        val pattern = "\\d+".toRegex()
        val params = arrayListOf<String>()
        val strings = File(path).readStrings()

        for (index in 0 until strings.size) {
            val updatePattern = "update ${pattern.pattern}".toRegex()
            if (updatePattern.matches(strings[index])) {
                for (n in 1..10) {
                    val param = strings.getOrElse(index + n) { "" }.trim().lowercase()
                    if (param.isNotEmpty()) {
                        params.add(param)
                    }
                }
            }
        }
        return params
    }
}