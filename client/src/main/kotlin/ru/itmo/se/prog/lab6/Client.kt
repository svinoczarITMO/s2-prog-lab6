import org.koin.core.context.GlobalContext.startKoin
import ru.itmo.se.prog.lab6.ClientApp
import ru.itmo.se.prog.lab6.di.notKoinModule
import ru.itmo.se.prog.lab6.utils.*

fun main() {
    startKoin {
        modules(notKoinModule)
    }
    val commandManager = CommandManager()
    val validator = Validator()
    val write = PrinterManager()
    val read = ReaderManager()
    val serializer = Serializer()
    val tokenizator = Tokenizator()
    val clientApp = ClientApp()

    val kotlinIsBetterThanJava = true

    // Создание сокетного подключения к серверу
    while (kotlinIsBetterThanJava) {
        val flag = ::main.name // Получение потоков ввода и вывода для обмена данными с сервером

        // Отправка сообщения на сервер
        write.inConsole("> ")
        val readFromConsole = (readln().lowercase()).split(" ").toMutableList()
        readFromConsole.add(flag)
        if (commandManager.getCommand("ru.itmo.se.prog.lab6.commands", readFromConsole[0], "Command") != null) {
            val message = serializer.serializeString(readFromConsole)
            println(message)
            clientApp.request(message)
        }
    }
}