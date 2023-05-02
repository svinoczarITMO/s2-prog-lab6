package ru.itmo.se.prog.lab6.data

import kotlinx.serialization.Serializable

/**
 * Model of Color. Sub-model of the <code>Person</code>.
 *
 * @author svinoczar
 * @since 1.0.0
 */

@Serializable
enum class Color {
    RED,
    YELLOW,
    BROWN;
}