package ru.itmo.se.prog.lab6.commands

import ru.itmo.se.prog.lab6.data.Person
import ru.itmo.se.prog.lab6.data.types.ArgType
import ru.itmo.se.prog.lab6.data.types.LocationType
import ru.itmo.se.prog.lab6.data.types.StatusType
import ru.itmo.se.prog.lab6.utils.AddPersonFields
import ru.itmo.se.prog.lab6.utils.validation.Data
import java.util.*

/**
 * Adds new element in the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Add: ru.itmo.se.prog.lab6.commands.Command(ArgType.OBJECT, StatusType.USER, LocationType.SERVER) {
    private val set = AddPersonFields()

    override fun getName(): String {
        return "add"
    }

    override fun getDescription(): String {
        return getName() + " - добавляет новый элемент в коллекцию\n"
    }

    override fun execute(data: Data): String? {
        val element = data.obj
        element.id = if (collectionManager.collection.isNotEmpty()) collectionManager.collection.maxOf { it.id } + 1 else 1
        collectionManager.collection.add(element)
        val result = (message.getMessage("added"))
        return result
    }
}
