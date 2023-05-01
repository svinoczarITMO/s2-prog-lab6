package ru.se.itmo.prog.lab6.commands

/**
 * Deletes the collection item with the entered identifier.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class RemoveByID: Command() {
    override fun getName(): String {
        return "remove_by_id"
    }

    override fun getDescription(): String {
        return getName() + " --id - удаляет элемент из коллекции по его id\n"
    }

    override fun execute(args: Map<String, Any?>): String? {
        var result: String? = ""
        var flag = false
        val id: Int by args
        try {
            try {
                for (element in collectionManager.collection) {
                    if (element.id == id) {
                        collectionManager.collection.remove(element)
                        flag = true
                        break
                    }
                }
            } catch (e: ArrayIndexOutOfBoundsException) {
                result = e.message
                return result
            }

        if (flag) {
            result = (
                message.getMessage("by_id") +
                        id +
                        message.getMessage("removed")
            )
        } else if (!flag){
            result = (message.getMessage("invalid_id"))
        }
        } catch (e: ArrayIndexOutOfBoundsException) {
            result = (message.getMessage("InvalidArgument"))
            return result
        }
        return result
    }
}