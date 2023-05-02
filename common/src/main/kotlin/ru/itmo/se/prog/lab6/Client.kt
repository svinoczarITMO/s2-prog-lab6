import ru.itmo.se.prog.lab6.di.notKoinModule
import org.koin.core.context.GlobalContext.startKoin
import ru.itmo.se.prog.lab6.data.*
import ru.itmo.se.prog.lab6.utils.*
import java.io.PrintWriter
import java.net.*

fun main() {
    startKoin {
        modules(notKoinModule)
    }

    val host = "localhost"
    val port = 5000
    val collectionManager = CollectionManager()
    val validator = Validator()
    val logger = Logger()
    val write = PrinterManager()
    val commandManager = CommandManager()
    val message = Messages()

    logger.initDate(collectionManager)


    val socket = Socket(host, port)
    val outputStream = socket.getOutputStream()
    val printWriter = PrintWriter(outputStream, true)
    printWriter.println("Hello server!")

    val inputStream = socket.getInputStream()

    socket.close()

    while (true){
        val flag = ::main.name
        write.inConsole("> ")
        val readFromConsole = (readln().lowercase()).split(" ").toMutableList()
        readFromConsole.add(flag)
        if (commandManager.getCommand("ru.itmo.se.prog.lab6.commands", readFromConsole[0], "Command") != null ){
            val result = validator.validate(readFromConsole.toTypedArray())
            write.linesInConsole(result)
        } else {
            write.linesInConsole(message.getMessage("weird_command"))
        }
    }
}
