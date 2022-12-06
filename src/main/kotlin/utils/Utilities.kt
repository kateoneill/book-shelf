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
            .joinToString(separator = "\n") { book ->
                "Book #${book.bookID}" +
                    "\n Title: ${book.bookTitle}(${book.bookRating}*)   ${book.bookLength} pages" +
                    "\n Genre: ${book.bookGenre}  Pace:${book.bookPace}" +
                    "\n Is book owned: ${book.isBookOwned} " +
                    "\n This book is ${book.bookProgress}" +
                        "\n ~~~~~~~~~~~~~~~~~~~~~~~~~"
            }

    /**
     * formatting book list to make it display more readable
     */
    fun formatBookListString(notesToFormat: List<Book>): String =
        notesToFormat
            .joinToString(separator = "\n") { book ->
                "\tBook #${book.bookID}" +
                    "\n  \tTitle: ${book.bookTitle}(${book.bookRating}*)   ${book.bookLength} pages" +
                    "\n  \tGenre: ${book.bookGenre}  Pace:${book.bookPace}" +
                    "\n  \tIs book owned: ${book.isBookOwned} " +
                    "\n  \tThis book is ${book.bookProgress}\n" + "\n ~~~~~~~~~~~~~~~~~~~~~~~~~"
            }
}
