package models

import utils.Utilities

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

    /**
     * `add book to author`
     *
     * @return book that's been added
     */
    fun addBook(book: Book): Boolean {
        book.bookID = getBookId()
        return books.add(book)
    }

    /**
     *  count size of book array
     *
     * @return number of books for author
     */
    fun numberOfBooks() = books.size

    /**
     * find author
     *
     * @return book with corresponding ID
     */
    fun findOne(id: Int): Book? {
        return books.find { book -> book.bookID == id }
    }

    /**
     * delete book from author
     *
     * @return book that's been deleted
     */
    fun delete(id: Int): Boolean {
        return books.removeIf { book -> book.bookID == id }
    }

    /**
     * update book for author
     *
     * @return true if book has been updated
     */
    fun update(id: Int, newBook: Book): Boolean {
        val foundBook = findOne(id)

        // if the object exists, use the details passed in the newItem parameter to
        // update the found object in the Set
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

        // if the object was not found, return false, indicating that the update was not successful
        return false
    }

    /**
     * give list of books for author
     *
     * @return list of books
     */

    fun listBooks() =
        if (books.isEmpty()) "\tNO BOOKS ADDED"
        else Utilities.formatSetString(books)

    /**
     * check if book is marked as owned or not
     *
     * @return true if book is owned
     */

    fun checkBookOwnershipStatus(): Boolean {
        if (books.isNotEmpty()) {
            for (book in books) {
                if (!book.isBookOwned) {
                    return false
                }
            }
        }
        return true // a note with empty items can be archived, or all items are complete
    }

    /**
     * list books by page length smallest to largest
     *
     * @return list of books in page length order
     */
    fun listBooksInOrderOfPageLength(): String {
        return if (books.isEmpty()) "No books stored"
        else Utilities.formatBookListString(books.sortedBy { books -> books.bookLength })
    }

    /**
     * list books by rating
     *
     * @return books in rating order highest to lowest
     */

    fun listBooksByRating(): String {
        return if (books.isEmpty()) "No books stored"
        else Utilities.formatBookListString(books.sortedByDescending { books -> books.bookRating })
    }

    /**
     * print an author dashboard
     */

    fun authorDashboard(): String {
        return """
    >                    Dashboard for $firstName $surname
    >                    ---------------------------------
    > $biography
    > E-mail: $email
    > Site: $website
    > ----------------------------------------------------------------------------------------
    > 
    > Number of books: ${numberOfBooks()}
    > 
    > Genres: ${genres()}
    > 
    > Books:
    > ${bookRating()}
        
        
    """.trimMargin(">")
    }

    /**
     * gather genres for author
     *
     * @return all genres from books written by given author
     */
    fun genres(): String {
        var listOfGenres = ""
        for (book in books) {
            if (book.bookGenre.isEmpty()) {
                "no genres"
            } else {
                listOfGenres += "\t${book.bookGenre}\t"
            }
        }
        return if (listOfGenres == "") "No books found with genre"
        else listOfGenres
    }

    /**
     * combines book with some metadata in a string
     *
     * @return string with bookProgress, title, pace and star rating
     */
    fun bookRating(): String {
        var bookDetails = ""
        for (book in books) {
            bookDetails += "${book.bookProgress} = ${book.bookTitle}(${book.bookPace} pace)  :  ${book.bookRating} star\n"
        }
        return if (numberOfBooks() == 0) "No books"
        else bookDetails
    }

    override fun toString(): String {
        return "\n ID: $authorID    Name: $firstName $surname" +
            "\n $biography" +
            "\n Available at: $email " +
            "\n Find more on $website" +
            "\n Currently published by $publisher" +
            "\n Books: " +
            "\n       ${listBooks()}" +
            "\n---------------------------------------"
    }
}
