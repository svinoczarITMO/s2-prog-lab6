package ru.itmo.se.prog.lab6

import org.koin.core.context.GlobalContext.startKoin
import ru.itmo.se.prog.lab6.di.notKoinModule
import ru.itmo.se.prog.lab6.utils.Serializer
import java.net.ServerSocket
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    startKoin {
        modules(notKoinModule)
    }
    val server = ServerApp()
//    println("Server started on port")

    while (true) {
        server.start()
    }
}