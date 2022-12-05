package utils

import models.Author
import models.Book

object Utilities {

    //NOTE: JvmStatic annotation means that the methods are static (i.e. we can call them over the class
    //      name; we don't have to create an object of Utilities to use them.

    @JvmStatic
    fun validRange(numberToCheck: Int, min: Int, max: Int): Boolean {
        return numberToCheck in min..max
    }

    @JvmStatic
    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }

    @JvmStatic
    fun isValidID(id: Int, min: Int, max: Int): Boolean {
        return id in min..max
    }

    @JvmStatic
    fun formatListString(notesToFormat: List<Author>): String =
        notesToFormat
            .joinToString(separator = "\n") { author ->  "$author" }

    @JvmStatic
    fun formatSetString(itemsToFormat: Set<Book>): String =
        itemsToFormat
            .joinToString(separator = "\n") { book ->  "\t$book" }

    fun formatBookListString(notesToFormat: List<Book>): String =
        notesToFormat
            .joinToString(separator = "\n") { book ->  "$book" }

}
