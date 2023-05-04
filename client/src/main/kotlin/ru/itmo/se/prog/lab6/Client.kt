import org.koin.core.context.GlobalContext.startKoin
import ru.itmo.se.prog.lab6.ClientApp
import ru.itmo.se.prog.lab6.data.Messages
import ru.itmo.se.prog.lab6.di.notKoinModule
import ru.itmo.se.prog.lab6.utils.*
import ru.itmo.se.prog.lab6.utils.validation.ClientValidator
import java.io.File

fun main() {
    startKoin {
        modules(notKoinModule)
    }
    val commandManager = CommandManager()
    val validator = Validator()
    val clientValidator = ClientValidator()
    val write = PrinterManager()
    val read = ReaderManager()
    val message = Messages()
    val serializer = Serializer()
    val clientApp = ClientApp()
    val commandPackage = "ru.itmo.se.prog.lab6.commands"
    val kotlinIsBetterThanJava = true

    // Создание сокетного подключения к серверу
    while (kotlinIsBetterThanJava) {
        val flag = ::main.name // Получение потоков ввода и вывода для обмена данными с сервером

        // Отправка сообщения на сервер
        write.inConsole("> ")
        val readFromConsole = (readln().lowercase()).split(" ").toMutableList()
        readFromConsole.add(flag)
        if (commandManager.getCommand(commandPackage, readFromConsole[0], "Command") != null) {
            println(readFromConsole)
            val queue = clientValidator.validate(readFromConsole)
            for (data in queue) {
                val dataStr = serializer.serializeData(data)
                clientApp.request(dataStr)
            }
//            File("example.json").writeText(dataStr)
        } else {
            write.linesInConsole(message.getMessage("weird_command"))
        }
    }
}

//TODO: 1) Проверить работоспособность всех команд
//TODO: 1.1) info ✓
//TODO: 1.2) reorder ✓
//TODO: 1.3) change_collection ✓
//TODO: 1.4) count_by_hair_color --hairColor ✓
//TODO: 1.5) clear ✓
//TODO: 1.6) min_by_weight ✓
//TODO: 1.7) update <- ПОЧИНИТЬ
//TODO: 1.8) help ✓
//TODO: 1.9) save ✓
//TODO: 1.10) show ✓
//TODO: 1.11) remove_first ✓
//TODO: 1.12) group_counting_by_nationality ✓
//TODO: 1.13) exit <- ПОЧИНИТЬ
//TODO: 1.13) history ✓
//TODO: 1.14) execute_script --file_name <- ПОЧИНИТЬ
//TODO: 1.15) remove_by_id --id ✓
//TODO: 1.16) add <- ПОЧИНИТЬ
//TODO: 2) Переписать валидатор специально для клиента
//TODO: 3) "Починить" add command
//TODO: 4) "Починить" update command
//TODO: 5) "Починить" execute_script command
//TODO: 6) "Починить" exit command