package ru.itmo.se.prog.lab6

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab6.utils.Serializer
import ru.itmo.se.prog.lab6.utils.Validator
import java.io.*
import java.net.InetSocketAddress
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.util.logging.Logger


class ServerApp() : KoinComponent {
    private val ip = "localhost"
    private val port = 8844
    val serializer: Serializer by inject()
    val validator: Validator by inject()
    private val logger = Logger.getLogger("logger")

    fun start (){
        logger.info("Попытка запуска сервера...")
        try {
            val serverSocket = ServerSocketChannel.open()
            serverSocket.bind((InetSocketAddress(ip, port)))
            logger.info("Ожидание подключения...")
            val bufferReader = BufferedReader(InputStreamReader(System.`in`))
            while (serverSocket.socket().isBound) {
                if (bufferReader.ready()) {
                    val serverCommand = bufferReader.readLine()
                }
                val clientSocket = serverSocket.accept()
                request(clientSocket)
            }
        } catch (e: Exception) {
            logger.severe("Ошибка подключения.")
        }
    }

    private fun request (clientSocketChannel: SocketChannel){
        logger.info("Обработка запроса...")
        try {
            val input: InputStream = clientSocketChannel.socket().getInputStream()
            val bufferedReader = BufferedReader(InputStreamReader(input))
            val str_in = bufferedReader.readLine()?.trim()!!

            val deserializabledInput: Array<Any> = serializer.deserializeList(str_in).toTypedArray()
            val result = validator.validate(deserializabledInput)
            response(clientSocketChannel, result)
        } catch (e: Exception) {
            logger.severe(e.message + " Ошибка обработки запроса.")
        }
    }

    private fun response (clientSocketChannel: SocketChannel, result: String) {
        logger.info("Отправка ответа...")
        try {
            val output = PrintWriter(clientSocketChannel.socket().getOutputStream())
            output.write(result)
            println("result: $result")
            output.flush()
            clientSocketChannel.shutdownOutput()
        } catch (e: Exception) {
            logger.severe("Ошибка отправки ответа.")
        }
    }
}