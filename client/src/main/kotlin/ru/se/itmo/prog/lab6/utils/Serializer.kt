package ru.itmo.se.prog.lab6.utils

import ru.se.itmo.prog.lab6.data.Person
import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.*

/**
 * Basic class for serializing the data.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Serializer {
    /**
     * Serializes the list of Person objects.
     */
    fun serializeCollection(whatToSerialize: List<Person>): String {
        return Json.encodeToString(whatToSerialize)
    }

    /**
     * Deserializes the list of Person objects.
     */
    fun deserializeCollection(whatToDeserialize: String): List<Person> {
        return Json.decodeFromString<List<Person>>(whatToDeserialize)
    }
}

object DateAsStringSerializer : KSerializer<Date> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(value.toString())
    }
    val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
    override fun deserialize(decoder: Decoder): Date = dateFormat.parse(decoder.decodeString())
}