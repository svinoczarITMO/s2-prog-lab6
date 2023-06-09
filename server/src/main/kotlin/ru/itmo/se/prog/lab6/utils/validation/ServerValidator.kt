package ru.itmo.se.prog.lab6.utils.validation


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab6.data.types.LocationType
import ru.itmo.se.prog.lab6.utils.CommandManager
import ru.itmo.se.prog.lab6.utils.validation.Data
import java.io.File

class ServerValidator : KoinComponent {
    private val commandManager: CommandManager by inject()
    private val commandPackage = "ru.itmo.se.prog.lab6.commands"
    private val historyFile = File("D:\\ITMO\\2nd-semester\\prog-labs\\s2-prog-lab6\\server\\src\\main\\kotlin\\ru\\itmo\\se\\prog\\lab6\\data\\history.log")
    private val commandBuffer = historyFile.readLines().toMutableList()

    fun validate (data: Data): String? {
        val commandName = data.name
        if (commandBuffer.size == 7) {
            commandBuffer.removeAt(0)
            commandBuffer.add(commandName)
        } else {
            commandBuffer.add(commandName)
        }
        println(commandBuffer)

        historyFile.writeText(commandBuffer.joinToString())

        return if (data.locationType == LocationType.SERVER) {
            commandManager.getCommand(commandPackage, data.name, "Command")?.execute(data)
        } else {
            ""
        }
    }
}