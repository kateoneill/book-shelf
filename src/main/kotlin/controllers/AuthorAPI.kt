package controllers

import models.Author
import persistence.Serializer
import utils.Utilities
import utils.Utilities.formatListString
import utils.Utilities.isValidID
import java.util.ArrayList


class AuthorAPI(serializerType: Serializer) {

    private var authors = ArrayList<Author>()

    private var lastId = 0
    private fun getID() = lastId++

    fun add(author: Author): Boolean {
        author.authorID = getID()
        return authors.add(author)
    }

    fun delete(id: Int) = authors.removeIf { author -> author.authorID == id }

    fun findAuthor(authorID : Int) =  authors.find{ author -> author.authorID == authorID }

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
        if  (authors.isEmpty()) "No notes stored"
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
}




