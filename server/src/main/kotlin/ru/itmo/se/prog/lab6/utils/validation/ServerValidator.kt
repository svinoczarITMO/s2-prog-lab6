package ru.itmo.se.prog.lab6.utils.validation


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab6.utils.CommandManager
import ru.itmo.se.prog.lab6.utils.validation.Data

class ServerValidator : KoinComponent {
    private val commandManager: CommandManager by inject()
    private val commandPackage = "ru.itmo.se.prog.lab6.commands"

    fun validate (data: Data): String? {
        val
        return commandManager.getCommand(commandPackage, data.name, "Command")?.execute(data.args)
    }
}