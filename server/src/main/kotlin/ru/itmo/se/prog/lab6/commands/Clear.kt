package ru.itmo.se.prog.lab6.commands


import ru.itmo.se.prog.lab6.data.types.ArgType
import ru.itmo.se.prog.lab6.data.types.LocationType
import ru.itmo.se.prog.lab6.data.types.StatusType
import ru.itmo.se.prog.lab6.utils.validation.Data

/**
 * Clears the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Clear: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        return "clear"
    }

    override fun getDescription(): String {
        return getName() + " - очищает коллекцию\n"
    }

    override fun execute(data: Data): String? {
        val result = (message.getMessage("clear"))
        collectionManager.collection.clear()
        return result
    }
}