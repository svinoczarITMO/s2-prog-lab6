import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.itmo.se.prog.lab6.utils.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket

fun main() {
    val commandManager = CommandManager()
    val validator = Validator()
    val write = PrinterManager()
    val read = ReaderManager()
    val serializer = Serializer()

    val kotlinIsBetterThanJava = true

    val host = "localhost"
    val port = 12345


    // Создание сокетного подключения к серверу


    while (kotlinIsBetterThanJava) {
        val flag = ::main.name // Получение потоков ввода и вывода для обмена данными с сервером

        // Отправка сообщения на сервер
        write.inConsole("> ")
        val readFromConsole = (readln().lowercase()).split(" ").toMutableList()
        readFromConsole.add(flag)
        if (commandManager.getCommand("ru.se.itmo.prog.lab6.commands", readFromConsole[0], "Command") != null) {
            val message = Json.encodeToString(readFromConsole)
            write.linesInConsole(message)

            // Получение ответа от сервера
            println("Received response from server: ")
        }
    }
}