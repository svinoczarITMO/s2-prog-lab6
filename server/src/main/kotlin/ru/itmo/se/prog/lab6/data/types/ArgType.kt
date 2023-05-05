package ru.itmo.se.prog.lab6.data.types

import kotlinx.serialization.Serializable

@Serializable
enum class ArgType {
    NO_ARG,
    ONE_ARG,
    OBJECT,
    OBJECT_PLUS
}