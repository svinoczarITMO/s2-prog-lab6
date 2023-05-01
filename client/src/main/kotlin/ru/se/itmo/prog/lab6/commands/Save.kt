package ru.se.itmo.prog.lab6.commands

import ru.se.itmo.prog.lab6.commands.Command
import ru.se.itmo.prog.lab6.data.Person
import java.util.*

/**
 * Saves the collection in the file Collection.json.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Save: Command() {
    private val pathToFile = System.getenv("COLLECTION_VAR")
    override fun getName(): String {
        return "save"
    }

    override fun getDescription(): String {
        return getName() + " - сохраняет коллекцию в файл\n"
    }

    /**
     * execute method. Save collection to file
     */
    override fun execute(args: Map<String, Any?>): String? {
        var result: String? = ""
        val collection = Vector<Person>()
        collection.addAll(collectionManager.collection)
        val list = collectionManager.collectionToList()
        val jsonString = serializer.serializeCollection(list)
        write.toFile(jsonString, pathToFile)
        result = (message.getMessage("saved"))
        return result
    }
}