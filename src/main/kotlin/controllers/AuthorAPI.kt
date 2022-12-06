package controllers

import models.Author
import persistence.Serializer
import utils.Utilities.formatListString
import java.util.ArrayList

class AuthorAPI(serializerType: Serializer) {
    private var serializer: Serializer = serializerType

    private var authors = ArrayList<Author>()

    private var lastId = 0
    private fun getID() = lastId++

    fun add(author: Author): Boolean {
        author.authorID = getID()
        return authors.add(author)
    }

    fun delete(id: Int) = authors.removeIf { author -> author.authorID == id }

    fun findAuthor(authorID: Int) = authors.find { author -> author.authorID == authorID }

    fun update(id: Int, author: Author?): Boolean {
        // find the note object by the index number
        val foundAuthor = findAuthor(id)

        // if the note exists, use the note details passed as parameters to update the found note in the ArrayList.
        if ((foundAuthor != null) && (author != null)) {
            foundAuthor.firstName = author.firstName
            foundAuthor.surname = author.surname
            foundAuthor.biography = author.biography
            foundAuthor.email = author.email
            foundAuthor.publisher = author.publisher
            foundAuthor.website = author.website
            return true
        }

        // if the note was not found, return false, indicating that the update was not successful
        return false
    }

    fun listAllAuthors(): String =
        if (authors.isEmpty()) "No notes stored"
        else formatListString(authors)

    fun numberOfAuthors(): Int {
        return authors.size
    }

    fun searchByName(searchString: String) =
        formatListString(
            authors.filter { author -> author.firstName.contains(searchString, ignoreCase = true) }
        )

    fun searchByEmail(searchString: String) =
        formatListString(
            authors.filter { author -> author.email.contains(searchString, ignoreCase = true) }
        )

    fun listAuthorsByPublisher(publisher: String): String =
        if (authors.isEmpty()) "No authors stored"
        else {
            val listOfAuthors = formatListString(authors.filter { author -> author.publisher == publisher })
            if (listOfAuthors.equals("")) "No authors with publisher: $publisher"
            else "$publisher: $listOfAuthors"
        }

    fun listAuthorsBySurname(surname: String): String =
        if (authors.isEmpty()) "No authors stored"
        else {
            val listOfAuthors = formatListString(authors.filter { author -> author.surname == surname })
            if (listOfAuthors.equals("")) "No authors with publisher: $surname"
            else "$surname: $listOfAuthors"
        }

    fun numberOfAuthorsByPublisher(publisher: String): Int = authors.count {
        author: Author ->
        author.publisher == publisher
    }

    fun numberOfAuthorsBySurname(surname: String): Int = authors.count {
        author: Author ->
        author.surname == surname
    }

    fun searchBookByTitle(searchString: String): String {
        return if (numberOfAuthors() == 0) "No authors stored"
        else {
            var listOfAuthor = ""
            for (author in authors) {
                for (book in author.books) {
                    if (book.bookTitle.contains(searchString, ignoreCase = true)) {
                        listOfAuthor += "${author.authorID}: ${author.firstName} \n\t${book}\n"
                    }
                }
            }
            if (listOfAuthor == "") "No books found for: $searchString"
            else listOfAuthor
        }
    }

    fun searchBookByGenre(searchString: String): String {
        return if (numberOfAuthors() == 0) "No authors stored"
        else {
            var listOfAuthor = ""
            for (author in authors) {
                for (book in author.books) {
                    if (book.bookGenre.contains(searchString, ignoreCase = true)) {
                        listOfAuthor += "${author.authorID}: ${author.firstName} \n\t${book}\n"
                    }
                }
            }
            if (listOfAuthor == "") "No books found for: $searchString"
            else listOfAuthor
        }
    }

    fun searchBookByLength(searchInt: Int): String {
        return if (numberOfAuthors() == 0) "No authors stored"
        else {
            var listOfAuthor = ""
            for (author in authors) {
                for (book in author.books) {
                    if (book.bookLength == searchInt) {
                        listOfAuthor += "${author.authorID}: ${author.firstName} \n\t${book}\n"
                    }
                }
            }
            if (listOfAuthor == "") "No books found for: $searchInt"
            else listOfAuthor
        }
    }

    fun numberOfBooksMarkedOwned(): Int {
        var numberOfBooksMarkedOwned = 0
        for (author in authors) {
            for (book in author.books) {
                if (book.isBookOwned) {
                    numberOfBooksMarkedOwned++
                }
            }
        }
        return numberOfBooksMarkedOwned
    }

    fun listBooksMarkedOwned(): String =
        if (numberOfAuthors() == 0) "No authors stored"
        else {
            var listOfBooksMarkedOwned = ""
            for (author in authors) {
                for (book in author.books) {
                    if (!book.isBookOwned) {
                        listOfBooksMarkedOwned += author.firstName + " " + author.surname + ": " + book.bookTitle + "\n"
                    }
                }
            }
            listOfBooksMarkedOwned
        }

    @Throws(Exception::class)
    fun load() {
        authors = serializer.read() as ArrayList<Author>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(authors)
    }
}
