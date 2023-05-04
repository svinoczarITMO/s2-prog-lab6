package ru.itmo.se.prog.lab6.utils

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab6.data.Person
import java.io.File

/**
 * Loads actual collection from Collection.xml.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Loader: KoinComponent {
    private val pathToFile = System.getenv("SERVER_COLLECTION_VAR")
    private val collectionManager: CollectionManager by inject()
    private val serializer: Serializer by inject()

    /**
     * Loads collection from json file.
     */
    fun load () {
        collectionManager.collection = serializer.deserializeCollection(File(pathToFile).readText()) as MutableCollection<Person>
    }
}