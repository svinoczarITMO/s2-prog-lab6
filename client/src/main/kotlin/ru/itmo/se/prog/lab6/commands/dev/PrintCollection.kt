package ru.itmo.se.prog.lab6.commands.dev


import ru.itmo.se.prog.lab6.commands.Command
import ru.itmo.se.prog.lab6.data.types.ArgType
import ru.itmo.se.prog.lab6.data.types.LocationType
import ru.itmo.se.prog.lab6.data.types.StatusType
import ru.itmo.se.prog.lab6.utils.validation.Data

/**
 * Outputs all items in the collection as units.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class PrintCollection: Command(ArgType.NO_ARG, StatusType.ADMIN, LocationType.SERVER) {
    override fun getName(): String {
        return "print"
    }

    override fun getDescription(): String {
        return getName() + " - выводит в терминал все объекты коллекции в развернутом виде\n"
    }
    override fun execute(data: Data): String {
        val result = (collectionManager.collection.toString())
        return result
    }
}
