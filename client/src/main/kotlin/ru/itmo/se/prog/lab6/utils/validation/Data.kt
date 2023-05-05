package ru.itmo.se.prog.lab6.utils.validation

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import ru.itmo.se.prog.lab6.data.Person
import ru.itmo.se.prog.lab6.data.types.ArgType
import ru.itmo.se.prog.lab6.data.types.LocationType
import ru.itmo.se.prog.lab6.data.types.StatusType

@Serializable
data class Data(
    var name: String,
    var oneArg: String,
    var obj: Person,
    var placeFlag: String,
    var argType: ArgType,
    var statusType: StatusType,
    var locationType: LocationType
)
