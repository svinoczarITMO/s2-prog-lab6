package ru.itmo.se.prog.lab6

import org.koin.core.component.KoinComponent
import ru.itmo.se.prog.lab6.utils.Serializer
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.RuntimeException
import java.net.InetSocketAddress
import java.nio.channels.SocketChannel
import java.util.logging.Logger

class ClientApp (private val serverAdress: String, private val serverPort: Int): KoinComponent {
    private val ip = "localhost"
    private val port = 3678
    val serializer = Serializer()
    private val logger = Logger.getLogger("logger")

    fun connection(): SocketChannel {
        return try {
            val clientSocket = SocketChannel.open()
            clientSocket.socket().connect(InetSocketAddress(ip, port))
            clientSocket
        } catch (e: RuntimeException) {
            println("Ошибка подключения.")
            SocketChannel.open(InetSocketAddress(ip, port))
            throw e
        }
    }

    fun requestHandler(obj: String) {
        try {
            val clientSocket = connection()
            if (clientSocket.isConnected) {
                val objectOutputStream = ObjectOutputStream(clientSocket.socket().getOutputStream())
                objectOutputStream.writeObject(obj)
                responseHandler(clientSocket, obj)
            }
        } catch (e: Exception) {
            println("Ошибка запроса.")
        }
    }

    fun responseHandler(clientSocket: SocketChannel, obj: String) {

        val objectInputStream = ObjectInputStream(clientSocket.socket().getInputStream())
        val response = objectInputStream.readObject()

    }

}