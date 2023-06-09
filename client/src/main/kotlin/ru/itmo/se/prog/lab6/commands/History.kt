package ru.itmo.se.prog.lab6.commands


import ru.itmo.se.prog.lab6.data.types.ArgType
import ru.itmo.se.prog.lab6.data.types.LocationType
import ru.itmo.se.prog.lab6.data.types.StatusType
import ru.itmo.se.prog.lab6.utils.validation.Data
import java.util.*

/**
 * Outputs the last 7 commands.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class History: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        return "history"
    }

    override fun getDescription(): String {
        return getName() + " - выводит последние 7 команд (без их аргумента)\n"
    }

    override fun execute(data: Data): String? {
        val result: String? = ""
//        val buffer
//        result = (message.getMessage("last_commands"))
//        for (command in buffer) {
//            result += (command) + "\n"
//        }
//        result += "\n"
        return result
    }
}