package ru.itmo.se.prog.lab6.commands


import ru.itmo.se.prog.lab6.data.types.ArgType
import ru.itmo.se.prog.lab6.data.types.LocationType
import ru.itmo.se.prog.lab6.data.types.StatusType
import ru.itmo.se.prog.lab6.utils.validation.Data
import kotlin.system.exitProcess

/**
 * Terminates the programm.
 *
 * @author svinoczar
 * @since 1.0.0
 */

@Suppress("UNREACHABLE_CODE")
class Exit: Command(ArgType.NO_ARG, StatusType.USER, LocationType.CLIENT) {
    override fun getName(): String {
        return "exit"
    }

    override fun getDescription(): String {
        return getName() + " - завершает программу\n"
    }

    override fun execute(data: Data): String? {
        val result: String? = ""
        exitProcess(1)
        return result
    }
}