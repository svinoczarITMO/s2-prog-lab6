package ru.itmo.se.prog.lab6.commands

import ru.itmo.se.prog.lab6.utils.Logger
import java.io.PrintWriter

/**
 * Outputs information about the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Info: Command() {
    private val logger = Logger()
    override fun getName(): String {
        return "info"
    }

    override fun getDescription(): String {
        return getName() + " - выводит  в стандартный поток вывода информацию о коллекции " +
                "(тип, дата инициализации, количество элементов и т.д.)\n"
    }

    override fun execute(args: Map<String, Any?>): String? {
        val output = PrintWriter()
        var result: String? = ""
        val type = collectionManager.getType() //Работает некорректно с пустой коллекций (Выводит "Тип: ArrayList")
        val size = collectionManager.collection.size
        val initDate = logger.initDate(collectionManager)
        result = (
                    "Тип: " + type + "\n"
                    + "Размер: " + size + "\n"
                    + "Дата инициализации: " + initDate
                )
        return result
    }
}
