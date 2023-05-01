package ru.se.itmo.prog.lab6.commands

/**
 * Changes the collection type.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class ChangeCollection: Command() {
    override fun getName(): String {
        return "change_collection"
    }

    override fun getDescription(): String {
        return getName() + " - изменяет тип коллекции.)\n"
    }

    override fun execute(args: Map<String, Any?>): String? {
        val type: String by args
        var result: String? = ""
        try {
            collectionManager.changeType(type.toString())
        } catch (e: NullPointerException) {
            result = (message.getMessage("NoSuchType"))
            result += printSupportedTypes()
            return result
        }
        result = message.getMessage("type_changed") + collectionManager.getType()
        return result
    }

    /**
     * Outputs the supported collection types.
     *
     * @author svinoczar
     * @since 1.0.0
     */
    private fun printSupportedTypes() : String? {
        var output = message.getMessage("supported_types")
        for (type in collectionManager.getSupportedCollectionTypes()) {
            output += type.key + "\n"
        }
        return output
    }
}