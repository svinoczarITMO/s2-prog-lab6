import ru.itmo.se.prog.lab6.di.notKoinModule
import org.koin.core.context.GlobalContext.startKoin
import ru.itmo.se.prog.lab6.data.Messages
import ru.itmo.se.prog.lab6.utils.*

fun main() {
    startKoin {
        modules(notKoinModule)
    }

    val collectionManager = CollectionManager()
    val validator = Validator()
    val loader = Loader()
    val logger = Logger()
    val write = PrinterManager()
    val commandManager = CommandManager()
    val message = Messages()

    logger.initDate(collectionManager)
    loader.load()

    while (true){
        val flag = ::main.name
        write.inConsole("> ")
        val readFromConsole = (readln().lowercase()).split(" ").toMutableList()
        readFromConsole.add(flag)
        if (commandManager.getCommand("commands", readFromConsole[0], "Command") != null ){
            val result = validator.validate(readFromConsole.toTypedArray())
            write.linesInConsole(result)
        } else {
            write.linesInConsole(message.getMessage("weird_command"))
        }
    }
}
