package ru.itmo.se.prog.lab6

import org.koin.core.component.KoinComponent
import ru.itmo.se.prog.lab6.utils.PrinterManager
import ru.itmo.se.prog.lab6.utils.Serializer
import java.io.*
import java.net.InetSocketAddress
import java.nio.channels.SocketChannel
import java.util.logging.Logger

class ClientApp (): KoinComponent {
    private val ip = "localhost"
    private val port = 8844
    private val logger = Logger.getLogger("logger")
    val serializer = Serializer()
    val write = PrinterManager()

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

    fun request(obj: String) {
        try {
            val clientSocket = connection()
            if (clientSocket.isConnected) {
                println("SOEDINENO")
                val output = PrintWriter(clientSocket.socket().getOutputStream())
                println("obj = $obj")
                output.write(obj)
                output.flush()
                println("отправлено!")
                clientSocket.shutdownOutput()
                println("Поток вывода закрыт")
                response(clientSocket, obj)
            }
        } catch (e: Exception) {
            println("Ошибка запроса.")
        }
    }

    fun response(clientSocket: SocketChannel, obj: String) {
        try {
            val input: InputStream = clientSocket.socket().getInputStream()
            println("input: $input")
            val bufferedReader = BufferedReader(InputStreamReader(input))
            println("bufferedReader: $bufferedReader")
            val str_in = bufferedReader.readLine()?.trim()!!
//            write.linesInConsole(str_in)
            println("str_in: $str_in")
            clientSocket.socket().close()
        } catch (e: Exception) {
            println("Ошибка при получении ответа от сервера.")
        }
    }

}