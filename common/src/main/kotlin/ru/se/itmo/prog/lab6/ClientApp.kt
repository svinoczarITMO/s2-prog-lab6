package ru.se.itmo.prog.lab6

import org.koin.core.component.KoinComponent
import mu.KotlinLogging
import java.nio.channels.SocketChannel

class ClientApp (private val serverAdress: String, private val serverPort: Int): KoinComponent {
    private val logger = KotlinLogging.logger {}

    private lateinit var channel: SocketChannel

    fun start(map: HashMap<String, Command>, collectionHandler: CollectionHandler) {
        openServerSocket()
        while (serverSocketChannel != null) {
            logger.log(Level.INFO, "Ожидание подключения...")
            try {
                this.clientSocket = serverSocketChannel.accept()
                logger.log(Level.FINER, "Подключение успешно")
                processRequest(map)
            } catch (ioe: IOException) {
                logger.log(Level.SEVERE, "Не удалось подключиться к клиенту: ", ioe.message)
            } catch (ioe: ClassNotFoundException) {
                logger.log(Level.SEVERE, "Ошибка в полученном запросе: ", ioe.message)
            }
        }
        closeServerSocket()
    }
}