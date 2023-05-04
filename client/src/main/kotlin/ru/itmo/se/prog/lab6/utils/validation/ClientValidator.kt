package ru.itmo.se.prog.lab6.utils.validation

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject
import ru.itmo.se.prog.lab6.data.*
import ru.itmo.se.prog.lab6.data.types.ArgType
import ru.itmo.se.prog.lab6.data.types.LocationType
import ru.itmo.se.prog.lab6.data.types.StatusType
import ru.itmo.se.prog.lab6.utils.AddPersonFields
import ru.itmo.se.prog.lab6.utils.CommandManager
import java.util.Date

class ClientValidator: KoinComponent {
    private val commandManager: CommandManager by inject()
    private val message: Messages by inject()
    private val commandPackage = "ru.itmo.se.prog.lab6.commands"
    private val addPersonFields = AddPersonFields()
    private var params = arrayListOf("null parameter", "null parameter", "null parameter", "null parameter", "null parameter",
        "null parameter", "null parameter", "null parameter", "null parameter", "null parameter")
    private val dataObj = Data("command", mutableListOf("none"), Person(
        0,"Nikita", Coordinates(1.4f, 8.8f), Date(),180, 68,
                Color.YELLOW, Country.VATICAN, Location(1,2,3)),
        "main", ArgType.NO_ARG, StatusType.USER, LocationType.CLIENT)

    fun validate (data: MutableList<String>): Data {
        val commandName = data[0]
        val oneArg = data[1]
        val placeFlag = data[-1]
        val command = commandManager.getCommand(commandPackage, commandName, "Command")
        var isExecuteScript = false

        dataObj.name = commandName
        dataObj.placeFlag = oneArg
        dataObj.argType = command?.arg!!
        dataObj.statusType = command.status
        dataObj.locationType = command.location

        if (commandName == "execute_script") {
            isExecuteScript = true
        }

        when (command.arg) {
            ArgType.NO_ARG -> {
                dataObj.args = mutableListOf(null)
            }
            ArgType.ONE_ARG -> {
                dataObj.args = mutableListOf(oneArg)
            }
            ArgType.OBJECT -> {
                val obj = makeAnObject(placeFlag)
            }
            ArgType.OBJECT_PLUS -> {
                val obj = makeAnObject(placeFlag)
                dataObj.args = mutableListOf(oneArg)
            }
        }
        return dataObj
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
}