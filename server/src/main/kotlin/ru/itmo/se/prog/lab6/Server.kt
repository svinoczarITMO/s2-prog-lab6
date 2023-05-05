package ru.itmo.se.prog.lab6

import org.koin.core.context.GlobalContext.startKoin
import ru.itmo.se.prog.lab6.di.notKoinModule
import ru.itmo.se.prog.lab6.utils.Loader
import ru.itmo.se.prog.lab6.utils.Serializer
import java.io.File
import java.net.ServerSocket
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    startKoin {
        modules(notKoinModule)
    }
    val historyFile = File("D:\\ITMO\\2nd-semester\\prog-labs\\s2-prog-lab6\\server\\src\\main\\kotlin\\ru\\itmo\\se\\prog\\lab6\\data\\history.log")
    historyFile.writeText("")
    val loader = Loader()
    val server = ServerApp()
//    println("Server started on port")
    loader.load()
    while (true) {
        server.start()
    }
}