package ru.itmo.se.prog.lab6

import org.koin.core.component.KoinComponent
import ru.itmo.se.prog.lab6.utils.Serializer
import java.net.InetSocketAddress
import java.net.Socket
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import ru.se.itmo.prog.lab6.utils.ResultModule
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.logging.Level
import java.util.logging.Logger


class ServerApp() : KoinComponent {
    private val ip = "localhost"
    private val port = 3678
    val serializer = Serializer()
    private val logger = Logger.getLogger("logger")


    fun startServer (collection: String, path: String) {
        logger.log(Level.INFO, "Ожидание подключения...")
        try {
            val serverSocket = ServerSocketChannel.open()
            serverSocket.bind(InetSocketAddress(ip, port))
            val bufferReader = BufferedReader(InputStreamReader(System.`in`))
            while (serverSocket != null) {
                if (bufferReader.ready()) {
                    val serverCommand = bufferReader.readLine()
                }
                val clientSocket = serverSocket.accept()
                handleRequest(clientSocket, collection, path)
            }
            serverSocket?.close()
        } catch (e: Exception) {
            logger.log(Level.SEVERE, "Ошибка подключения.")
        }
    }

    private fun handleRequest (clientSocketChannel: SocketChannel, collection: String, path: String){
        logger.log(Level.SEVERE, "Обработка запроса...")
        try {
            val objectInputStream = ObjectInputStream(clientSocketChannel.socket().getInputStream())
            val result = objectInputStream.readObject() as ResultModule
        } catch (e: Exception) {
            logger.log(Level.SEVERE, "Ошибка обработки запроса.")
        }
    }

    private fun handleResponse (clientSocketChannel: SocketChannel, response: Response) {
        logger.log(Level.INFO, "Отправка ответа...")
        try {
            val objectOutputStream = ObjectOutputStream(clientSocketChannel.socket().getOutputStream())
            objectOutputStream.writeObject(response)
        } catch (e: Exception) {
            logger.log(Level.SEVERE, "Ошибка отправки ответа.")
        }
    }




    fun handleClientRequestBRUH(clientSocket: Socket) {
        try {
//            val input = bufferedReader(clientSocket.getInputStream())
            val input = clientSocket.getInputStream().bufferedReader()
            val output = clientSocket.getOutputStream().bufferedWriter()

            println("Ожидание...")

            // Чтение сообщения от клиента
            val message = (input.readLine())
            println("Received message from ${clientSocket.inetAddress.hostAddress}: $message")

            // Отправка ответа клиенту
            output.write("Hello from server!")
            output.newLine()

            println("Connection closed for ${clientSocket.inetAddress.hostAddress}")
        } catch (e: Exception) {
            println("Error occurred while handling client request: ${e.message}")
        }
    }
}