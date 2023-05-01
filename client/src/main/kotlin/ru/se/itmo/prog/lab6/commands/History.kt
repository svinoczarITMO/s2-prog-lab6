package ru.se.itmo.prog.lab6.commands

import java.util.*

/**
 * Outputs the last 7 commands.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class History: Command() {
    override fun getName(): String {
        return "history"
    }

    override fun getDescription(): String {
        return getName() + " - выводит последние 7 команд (без их аргумента)\n"
    }

    override fun execute(args: Map<String, Any?>): String? {
        var result: String? = ""
        val buffer: LinkedList<String> by args
        result = (message.getMessage("last_commands"))
        for (command in buffer) {
            result += (command) + "\n"
        }
        result += "\n"
        return result
    }
}