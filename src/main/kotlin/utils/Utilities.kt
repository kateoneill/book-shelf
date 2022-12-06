package utils

import models.Author
import models.Book

object Utilities {

    // NOTE: JvmStatic annotation means that the methods are static (i.e. we can call them over the class
    //      name; we don't have to create an object of Utilities to use them.

    /**
     * checking if value is in a valid range
     */
    @JvmStatic
    fun validRange(numberToCheck: Int, min: Int, max: Int): Boolean {
        return numberToCheck in min..max
    }

    /**
     * checking if id is valid
     */
    @JvmStatic
    fun isValidID(id: Int, min: Int, max: Int): Boolean {
        return id in min..max
    }

    /**
     * formatting author list to make it display more readable
     */
    @JvmStatic
    fun formatListString(notesToFormat: List<Author>): String =
        notesToFormat
            .joinToString(separator = "\n") { author -> author.toString() }

    /**
     * formatting book set to display neater
     */
    @JvmStatic
    fun formatSetString(itemsToFormat: Set<Book>): String =
        itemsToFormat
            .joinToString(separator = "\n") { book -> book.toString()  }

    /**
     * formatting book list to make it display more readable
     */
    fun formatBookListString(notesToFormat: List<Book>): String =
        notesToFormat
            .joinToString(separator = "\n") { book -> book.toString() }
}
