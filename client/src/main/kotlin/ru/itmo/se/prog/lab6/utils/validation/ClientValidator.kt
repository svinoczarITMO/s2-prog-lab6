package ru.itmo.se.prog.lab6.utils.validation

import org.jetbrains.kotlin.konan.file.File
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab6.data.*
import ru.itmo.se.prog.lab6.data.types.ArgType
import ru.itmo.se.prog.lab6.data.types.LocationType
import ru.itmo.se.prog.lab6.data.types.StatusType
import ru.itmo.se.prog.lab6.utils.AddPersonFields
import ru.itmo.se.prog.lab6.utils.CommandManager
import ru.itmo.se.prog.lab6.utils.PrinterManager
import java.util.Date

class ClientValidator: KoinComponent {
    private val commandManager: CommandManager by inject()
    private val message: Messages by inject()
    private val commandPackage = "ru.itmo.se.prog.lab6.commands"
    private val addPersonFields = AddPersonFields()
    private val write: PrinterManager by inject()
    private var params = arrayListOf("null parameter", "null parameter", "null parameter", "null parameter", "null parameter",
        "null parameter", "null parameter", "null parameter", "null parameter", "null parameter")
    private val dataObj = Data("command", "none",
        Person(0,"Nikita", Coordinates(1.4f, 8.8f), Date(),180, 68, Color.YELLOW, Country.VATICAN, Location(1,2,3)),
        "main", ArgType.NO_ARG, StatusType.USER, LocationType.CLIENT)

    fun validate (data: MutableList<String>): ArrayList<Data> {
        val commandName = data[0]
        val oneArg = data[1]
        val placeFlag = data.last()
        val command = commandManager.getCommand(commandPackage, commandName, "Command")
        var isExecuteScript = false
        val dataQueue = arrayListOf<Data>()

        dataObj.name = commandName
        dataObj.placeFlag = placeFlag
        dataObj.argType = command?.arg!!
        dataObj.statusType = command.status
        dataObj.locationType = command.location

        if (commandName == "execute_script") {
            isExecuteScript = true
        }

        if (command.location == LocationType.SERVER) {
            if (!isExecuteScript) {
                when (command.arg) {
                    ArgType.NO_ARG -> {
                        dataObj.oneArg = ""
                    }

                    ArgType.ONE_ARG -> {
                        dataObj.oneArg = oneArg
                    }

                    ArgType.OBJECT -> {
                        val obj = makeAnObject(placeFlag)
                        dataObj.obj = obj
                    }

                    ArgType.OBJECT_PLUS -> {
                        val obj = makeAnObject(placeFlag)
                        dataObj.obj = obj
                        dataObj.oneArg = oneArg
                    }
                }
                dataQueue.add(dataObj)
            } else {
                //НЕ РАБОТАЕТ С ADD и UPDATE
                val commandsQueue = preValidation(oneArg)
                if (commandsQueue.contains(arrayOf("ERROR"))) {
                    write.linesInConsole(message.getMessage("recursion"))
                } else {
                    for (element in commandsQueue) {
                        val tmp = validate(element.toMutableList())
                        tmp.forEach { dataQueue.add(it) }
                    }
                }
            }
            return dataQueue
        } else {
            write.linesInConsole(command.execute(dataObj))
            dataQueue.add(dataObj)
            return dataQueue
        }
    }

    private fun makeAnObject (placeFlag: String): Person {
        return Person(0,
            addPersonFields.name(params[0], placeFlag),
            Coordinates(
                addPersonFields.coordinateX(params[1], placeFlag),
                addPersonFields.coordinateY(params[2], placeFlag)),
            Date(),
            addPersonFields.height(params[3], placeFlag),
            addPersonFields.weight(params[4], placeFlag),
            addPersonFields.hairColor(params[5], placeFlag),
            addPersonFields.nationality(params[6], placeFlag),
            Location(
                addPersonFields.locationX(params[7], placeFlag),
                addPersonFields.locationY(params[8], placeFlag),
                addPersonFields.locationZ(params[9], placeFlag)
            )
        )
    }

    private fun preValidation (path: String): ArrayList<Array<String>> {
        val commands = arrayListOf<Array<String>>()
        val errorArray = arrayListOf<Array<String>>(arrayOf("ERROR"))
        val strings = File(path).readStrings()
        val params = arrayListOf<String>()

        if (strings.isNotEmpty()) {
            for (index in 0 until strings.size) {
                val arr = arrayListOf<String>()
                val currentLine = strings[index].split(" ")
                val currentCommand = commandManager.getCommand(commandPackage, currentLine[0], "Command")
                if (currentCommand != null) {
                    if (currentLine[0] == "execute_script" && currentLine.last() == path) {
                        return errorArray
                    }
                    if (currentCommand.arg == ArgType.OBJECT || currentCommand.arg == ArgType.OBJECT_PLUS) {
                        for (n in 1..10) {
                            val param = strings.getOrElse(index + n) { "" }.trim().lowercase()
                            if (param.isNotEmpty()) {
                                params[n - 1] = param
                            }
                        }
                        //команда, список полей
                        currentLine.forEach { arr.add(it) }
                        params.forEach { arr.add(it) }
                        commands.add(arr.toTypedArray())
                    } else {
                        //команда, список аргументов
                        currentLine.forEach { arr.add(it) }
                        commands.add(arr.toTypedArray())
                    }
                }
            }
        }
        return commands
    }
}