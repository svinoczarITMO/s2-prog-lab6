package ru.itmo.se.prog.lab6.utils.validation


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab6.utils.CommandManager
import ru.itmo.se.prog.lab6.utils.validation.Data
import java.io.File

class ServerValidator : KoinComponent {
    private val commandManager: CommandManager by inject()
    private val commandPackage = "ru.itmo.se.prog.lab6.commands"
    private val commandBuffer = File("\\server\\src\\main\\kotlin\\ru\\itmo\\se\\prog\\lab6\\data\\history.log").readLines().toMutableList()

    fun validate (data: Data): String? {
        val commandName = data.name
        if (commandBuffer.size == 7) {
            commandBuffer.removeAt(0)
            commandBuffer.add(commandName)
        } else {
            commandBuffer.add(commandName)
        }
        return commandManager.getCommand(commandPackage, data.name, "Command")?.execute(data)
    }
}