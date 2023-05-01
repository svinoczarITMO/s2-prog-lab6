package ru.itmo.se.prog.lab6.commands

/**
 * Deletes the first item in the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class RemoveFirst: Command() {
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