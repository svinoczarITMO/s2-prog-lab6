package ru.itmo.se.prog.lab6.data

import kotlinx.serialization.Serializable

/**
 * Model of Coordinates. Sub-model of the <code>Person</code>.
 *
 * @constructor x: Float, y: Float
 * @author svinoczar
 * @since 1.0.0
 */
@Serializable
class Coordinates (var x: Float, var y: Float) {
}