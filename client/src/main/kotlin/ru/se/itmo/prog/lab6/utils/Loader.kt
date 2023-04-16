package ru.itmo.se.prog.lab6.utils

import ru.itmo.se.prog.lab6.data.Person
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File

/**
 * Loads actual collection from Collection.xml.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Loader: KoinComponent {
    private val pathToFile = System.getenv("COLLECTION_VAR")
    private val collectionManager: CollectionManager by inject()
    private val serializer: Serializer by inject()

    /**
     * Loads collection from json file.
     */
    fun load () {
        collectionManager.collection = serializer.deserialize(File(pathToFile).readText()) as MutableCollection<Person>
    }
}