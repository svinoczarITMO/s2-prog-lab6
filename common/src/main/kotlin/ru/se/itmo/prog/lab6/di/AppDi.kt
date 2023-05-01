package ru.itmo.se.prog.lab6.di

import ru.itmo.se.prog.lab6.data.Messages
import org.koin.dsl.module
import ru.itmo.se.prog.lab6.utils.*

/**
 * Koin Module with all needed objects.
 *
 * @author svinoczar
 * @since 1.0.0
 */
val notKoinModule = module {
    single {
        PrinterManager()
    }

    single {
        ReaderManager()
    }

    single {
        Messages()
    }

    single {
        Validator()
    }

    single {
        CommandManager()
    }

    single {
        CollectionManager()
    }

    single {
        Serializer()
    }
}