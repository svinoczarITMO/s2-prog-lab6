package ru.se.itmo.prog.lab6.commands

import ru.se.itmo.prog.lab6.commands.Command
import ru.se.itmo.prog.lab6.data.Person
import java.util.*

/**
 * Turns the collection around.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Reorder: Command() {
    override fun getName(): String {
        return "reorder"
    }

    override fun getDescription(): String {
        return getName() + " - сортирует коллекцию в порядке, обратном нынешнему\n"
    }
    override fun execute(args: Map<String, Any?>): String? {
        var result: String? = ""
        val bufferVector: Vector<Person> = Vector()
        for (element in collectionManager.collection) {
            bufferVector.insertElementAt(element,0)
        }
        collectionManager.collection = bufferVector
        result = (message.getMessage("reordered"))
        return result
    }
}