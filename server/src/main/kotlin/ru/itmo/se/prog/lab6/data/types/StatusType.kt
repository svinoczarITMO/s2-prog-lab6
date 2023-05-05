package ru.itmo.se.prog.lab6.data.types

import kotlinx.serialization.Serializable


@Serializable
enum class StatusType {
    ADMIN,
    USER
}