package ru.itmo.se.prog.lab6.commands.dev


import ru.itmo.se.prog.lab6.commands.Command
import ru.itmo.se.prog.lab6.data.*
import ru.itmo.se.prog.lab6.data.types.ArgType
import ru.itmo.se.prog.lab6.data.types.LocationType
import ru.itmo.se.prog.lab6.data.types.StatusType
import ru.itmo.se.prog.lab6.utils.validation.Data

/**
 * Adds new element to the collection with no input arguments.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class FastAdd: Command(ArgType.NO_ARG, StatusType.ADMIN, LocationType.SERVER) {
    override fun getName(): String {
        return "fadd"
    }

    override fun getDescription(): String {
        return getName() + " - добавляет новый элемент в коллекцию без указания параметров элемента\n"
    }

    override fun execute(data: Data): String? {
        val id: Int = if (collectionManager.collection.isNotEmpty()) collectionManager.collection.maxOf { it.id } + 1000 else 1000
        val obj = Person(id, "Jesus", Coordinates(0f,0f), collectionManager.parseDate("Fri Jul 19 12:00:00 MSK 2023"), 305, 0, Color.RED, Country.USA, Location(0,0,0))
        collectionManager.collection.add(obj)
        val result = message.getMessage("added")
        return result
    }
}