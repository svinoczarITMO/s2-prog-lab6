package ru.itmo.se.prog.lab6.commands


import ru.itmo.se.prog.lab6.data.Country
import ru.itmo.se.prog.lab6.data.Person
import ru.itmo.se.prog.lab6.data.types.ArgType
import ru.itmo.se.prog.lab6.data.types.LocationType
import ru.itmo.se.prog.lab6.data.types.StatusType
import ru.itmo.se.prog.lab6.utils.validation.Data
import java.util.*

/**
 * Counts and groups elements by nationality.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class GroupCountingByNationality: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        return "group_counting_by_nationality"
    }

    override fun getDescription(): String {
        return getName() + " - группирует элементы коллекции по значению поля nationality, " +
                "выводит количество элементов в каждой группе\n"
    }

    override fun execute(data: Data): String? {
        var result: String? = ""
        val bufferVector = Vector<Person>()
        var counter = 0

        for (nationality in Country.values()) {
            val mainNationality = nationality.toString()
            var counterOfElementsInGroup = 0
            for (element in collectionManager.collection) {
                if (element.nationality.toString() == mainNationality) {
                    bufferVector.insertElementAt(element, counter)
                    counter += 1
                    counterOfElementsInGroup += 1
                }
            }
            result += ("В группе $nationality $counterOfElementsInGroup человек\n")
        }
        collectionManager.collection = bufferVector
        return result
    }
}
