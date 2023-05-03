package ru.itmo.se.prog.lab6.commands

/**
 * Outputs all commands and their descriptions.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Help: Command() {
    override fun getName(): String {
        return "help"
    }

    override fun getDescription(): String {
        return getName() + " - выводит справку по доступным командам\n"
    }

    override fun execute(args: Map<String, Any?>): String? {
        var result: String? = ""
        result = (message.getMessage("="))
        val classes = commandManager.parsePackage("ru.itmo.se.prog.lab6.commands", "Command")
            .filter { klass -> !klass.simpleName.equals("FastAdd") && !klass.simpleName.equals("PrintCollection") && !klass.simpleName.equals("GetElement") }
        for (klass in classes) {
            try {
                val command = klass.getConstructor().newInstance() as Command
                result += (command.getDescription())
            } catch (e: Exception) {
                result = e.message
            }
        }
        result += (message.getMessage("="))
        return result
    }
}