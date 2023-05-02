package ru.itmo.se.prog.lab6.exceptions

/**
 * @exception [CommandException] used if the command is not detected
 *
 * @author svinoczar
 * @since 1.0.0
 */
class CommandException(message: String?) : Throwable(message)