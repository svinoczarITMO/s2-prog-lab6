package ru.itmo.se.prog.lab6.commands


import ru.itmo.se.prog.lab6.data.Coordinates
import org.jetbrains.kotlin.konan.file.File
import ru.itmo.se.prog.lab6.data.Person
import ru.itmo.se.prog.lab6.data.types.ArgType
import ru.itmo.se.prog.lab6.data.types.LocationType
import ru.itmo.se.prog.lab6.data.types.StatusType
import ru.itmo.se.prog.lab6.utils.AddPersonFields
import ru.itmo.se.prog.lab6.utils.PrinterManager
import ru.itmo.se.prog.lab6.utils.validation.Data
import java.util.*

/**
 * Updates the collection item with the entered identifier.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Update: Command(ArgType.OBJECT_PLUS, StatusType.USER, LocationType.SERVER) {
    private val set = AddPersonFields()
    private val writeToConsole = PrinterManager()
    override fun getName(): String {
        return "update"
    }

    override fun getDescription(): String {
        return getName() + " - обновляет элемент коллекции по указанному id\n"
    }

    override fun execute(data: Data): String? {
        val id = data.oneArg.toInt()
        val element = data.obj
        element.id = id
        val bufferCollection = mutableListOf<Person>()
        for (el in collectionManager.collection) {
            if (el.id != element.id) {
                bufferCollection.add(el)
            } else {
                bufferCollection.add(element)
            }
        }
        collectionManager.collection = bufferCollection
        return message.getMessage("updated")
    }
}