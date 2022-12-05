package models

import utils.Utilities
import utils.Utilities.isValidID
import utils.Utilities.formatListString

data class Author(
    var authorID: Int = 0,
    var firstName: String,
    var surname: String,
    var biography: String,
    var email: String,
    var publisher: String,
    var website: String,
    var books: MutableSet<Book> = mutableSetOf()
) {

    private var lastBookId = 0
    private fun getBookId() = lastBookId++

    fun addBook(book: Book): Boolean {
        book.bookID = getBookId()
        return books.add(book)
    }

    fun numberOfBooks() = books.size

    fun findOne(id: Int): Book? {
        return books.find { book -> book.bookID == id }
    }

    fun delete(id: Int): Boolean {
        return books.removeIf { book -> book.bookID == id }
    }

    fun update(id: Int, newBook: Book): Boolean {
        val foundBook = findOne(id)

        //if the object exists, use the details passed in the newItem parameter to
        //update the found object in the Set
        if (foundBook != null) {
            foundBook.bookTitle = newBook.bookTitle
            foundBook.bookGenre = newBook.bookGenre
            foundBook.bookLength = newBook.bookLength
            foundBook.bookPace = newBook.bookPace
            foundBook.bookProgress = newBook.bookProgress
            foundBook.bookRating = newBook.bookRating
            foundBook.isBookOwned = newBook.isBookOwned
            return true
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false
    }

    fun listBooks() =
        if (books.isEmpty()) "\tNO BOOKS ADDED"
        else Utilities.formatSetString(books)


    fun checkBookOwnershipStatus(): Boolean {
        if (books.isNotEmpty()) {
            for (book in books) {
                if (!book.isBookOwned) {
                    return false
                }
            }
        }
        return true //a note with empty items can be archived, or all items are complete
    }

    fun listBooksInOrderOfPageLength(): String {
        return if (books.isEmpty()) "No books stored"
        else  Utilities.formatBookListString(books.sortedBy { books -> books.bookLength })
    }
}



