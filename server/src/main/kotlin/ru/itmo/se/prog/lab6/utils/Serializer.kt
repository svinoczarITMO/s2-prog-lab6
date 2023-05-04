package ru.itmo.se.prog.lab6.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import ru.itmo.se.prog.lab6.data.Person
import java.text.SimpleDateFormat
import java.util.*

/**
 * Basic class for serializing the data.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Serializer {
    private val json = Json { prettyPrint = true }

    /**
     * Serializes the list of Person objects.
     */
    fun serializeCollection(whatToSerialize: List<Person>): String {
        return json.encodeToString(whatToSerialize)
    }

    /**
     * Deserializes the list of Person objects.
     */
    fun deserializeCollection(whatToDeserialize: String): List<Person> {
        return json.decodeFromString<List<Person>>(whatToDeserialize)
    }

    /**
     *
     */
    fun serializeList(whatToSerialize: MutableList<String>): String {
        return json.encodeToString(whatToSerialize)
    }

    /**
     *
     */
    fun deserializeList(whatToDeserialize: String): List<String> {
        return json.decodeFromString<List<String>>(whatToDeserialize)
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