package ru.se.itmo.prog.lab6.commands

/**
 * Clears the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Clear: Command() {
    override fun getName(): String {
        return "clear"
    }

    override fun getDescription(): String {
        return getName() + " - очищает коллекцию\n"
    }

    override fun execute(args: Map<String, Any?>): String? {
        val result = (message.getMessage("clear"))
        collectionManager.collection.clear()
        return result
    }
}