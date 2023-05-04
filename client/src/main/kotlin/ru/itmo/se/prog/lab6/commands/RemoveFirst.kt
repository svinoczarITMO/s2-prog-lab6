package ru.itmo.se.prog.lab6.commands


import ru.itmo.se.prog.lab6.data.types.ArgType
import ru.itmo.se.prog.lab6.data.types.LocationType
import ru.itmo.se.prog.lab6.data.types.StatusType

/**
 * Deletes the first item in the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class RemoveFirst: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        return "remove_first"
    }

    override fun getDescription(): String {
        return getName() + " - удаляет первый элемент из коллекции\n"
    }
    override fun execute(args: Map<String, Any?>): String? {
        var result: String? = ""
        collectionManager.collection.remove(collectionManager.collection.first())
        result = (
            message.getMessage("first_element") +
                    message.getMessage("removed"))
        return result
    }
}