package ru.itmo.se.prog.lab6.commands

import kotlin.system.exitProcess

/**
 * Terminates the programm.
 *
 * @author svinoczar
 * @since 1.0.0
 */

@Suppress("UNREACHABLE_CODE")
class Exit: Command() {
    override fun getName(): String {
        return "exit"
    }

    override fun getDescription(): String {
        return getName() + " - завершает программу\n"
    }

    override fun execute(args: Map<String, Any?>): String? {
        val result: String? = ""
        exitProcess(1)
        return result
    }
}