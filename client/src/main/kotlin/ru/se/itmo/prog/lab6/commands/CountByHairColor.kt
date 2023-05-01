package ru.se.itmo.prog.lab6.commands

/**
 * Counts elements by hair color.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class CountByHairColor: Command() {
    override fun getName(): String {
        return "count_by_hair_color"
    }

    override fun getDescription(): String {
        return getName() + " --hairColor - выводит количество элементов, значение поля hairColor которых равно заданному\n"
    }

    override fun execute(args: Map<String, Any?>): String? {
        val color by args
        val copyVector = collectionManager.collection
        var counter = 0
        var result: String? = ""
        try {
            for (element in copyVector) {
                if (element.hairColor == color) {
                    counter += 1
                }
            }
            result = ("Количество людей с цветом волос \"${color.toString().capitalize()}\": $counter")
        } catch (e: IllegalArgumentException) {
            result = (message.getMessage("IllegalColor"))
        }
        return result
    }
}