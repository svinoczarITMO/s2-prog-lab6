package ru.itmo.se.prog.lab6

import ru.itmo.se.prog.lab6.utils.Serializer
import java.net.ServerSocket
import kotlin.concurrent.thread

fun main(args: Array<String>) {

    val server = ServerApp()
    val port = 12345
    val serverSocket = ServerSocket(port)
    println("Server started on port $port")

    while (true) {
        val clientSocket = serverSocket.accept()
        println("Client connected: ${clientSocket.inetAddress.hostAddress}")

        server.handleClientRequest(clientSocket)
    }
}