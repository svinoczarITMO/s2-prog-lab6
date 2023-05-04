package ru.itmo.se.prog.lab6.data


import java.util.*

/**
 * Model of Person. Main model of the program.
 *
 * @constructor id: Int, name: String, coordinates: Coordinates,
 * creationDate: Date, height: Int, weight: Long,
 * hairColor: Color, nationality: Country, location: Location
 * @author svinoczar
 * @since 1.0.0
 */

data class Person(
    var id: Int,
    var name: String,
    var coordinates: Coordinates,
    var creationDate: Date,
    var height: Int,
    var weight: Long,
    var hairColor: Color,
    var nationality: Country,
    var location: Location
) {}