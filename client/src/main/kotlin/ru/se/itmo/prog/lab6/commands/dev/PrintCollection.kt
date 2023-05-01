package ru.se.itmo.prog.lab6.commands.dev

import ru.se.itmo.prog.lab6.commands.Command

/**
 * Outputs all items in the collection as units.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class PrintCollection: Command() {
    override fun getName(): String {
        return "print"
    }

    override fun getDescription(): String {
        return getName() + " - выводит в терминал все объекты коллекции в развернутом виде\n"
    }
    override fun execute(args: Map<String, Any?>): String {
        val result = (collectionManager.collection.toString())
        return result
    }
}
