package ru.itmo.se.prog.lab6


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab6.commands.Save
import ru.itmo.se.prog.lab6.data.*
import ru.itmo.se.prog.lab6.data.types.ArgType
import ru.itmo.se.prog.lab6.data.types.LocationType
import ru.itmo.se.prog.lab6.data.types.StatusType
import ru.itmo.se.prog.lab6.utils.Serializer
import ru.itmo.se.prog.lab6.utils.validation.Data
import ru.itmo.se.prog.lab6.utils.validation.ServerValidator
import java.io.*
import java.net.InetSocketAddress
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.util.*
import java.util.logging.Logger

class ServerApp() : KoinComponent {
    private val ip = "localhost"
    private val port = 8844
    private val logger = Logger.getLogger("logger")
    val serializer: Serializer by inject()
    val serverValidator = ServerValidator()
    val saveData = Data("save", "save", Person(0,"SAVE", Coordinates(1.4f, 8.8f), Date(),180, 68, Color.YELLOW, Country.VATICAN, Location(1,2,3)), "main", ArgType.NO_ARG, StatusType.ADMIN, LocationType.SERVER)
    val save = Save()

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
            val dataStr = bufferedReader.readLine()?.trim()!!

            val data: Data = serializer.deserializeData(dataStr)
            println(data)
            val result = serverValidator.validate(data)
            response(clientSocketChannel, result)
        } catch (e: Exception) {
            logger.severe(e.message + " Ошибка обработки запроса.")
        }
    }

    private fun response (clientSocketChannel: SocketChannel, result: String?) {
        logger.info("Отправка ответа...")
        try {
            val output = PrintWriter(clientSocketChannel.socket().getOutputStream())
            output.write(result)
            println("result: $result")
            output.flush()
            clientSocketChannel.shutdownOutput()
            save.execute(saveData)
        } catch (e: Exception) {
            logger.severe("Ошибка отправки ответа.")
        }
    }
}