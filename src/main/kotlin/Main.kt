import controllers.AuthorAPI
import models.Author
import models.Book
import persistence.JSONSerializer
import utils.ScannerInput
import java.io.File
import utils.ValidateInput

private val authorAPI = AuthorAPI(JSONSerializer(File("authors.json")))

fun main(args: Array<String>) {
    runMenu();
}

fun mainMenu() : Int {
    return ScannerInput.readNextInt("""
          > ----------------------------------
          > |  Author MENU                   |
          > |   1) Add an author             |
          > |   2) List all authors          |
          > |   3) Update author             |
          > |   4) Delete an author          |
          > ----------------------------------
          > |  Book MENU                     |
          > |   5) Add a book to author      |
          > |   6) Update book               |
          > |   7) Delete book               |
          > |   8) List books                |
          > |   9) Mark book as owned        |
          > ----------------------------------
          > |   10) Search menu              |
          > |   11) List menu                |
          > |   12) Count menu               |
          > ----------------------------------
          > |   0) Exit                      |
          > ----------------------------------
          > Choose:""".trimMargin(">"))
}


fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1 -> addAuthor()
            2 -> listAllAuthors()
            3 -> updateAuthor()
            4 -> deleteAuthor()
            5 -> addBook()
            6 -> updateBook()
            7 -> deleteBook()
            8 -> listAuthorsBooks()
            9 -> markBookAsOwned()
            10 -> searchMenu()
            11 -> listMenu()
            12 -> countMenu()
            0 -> exitApp()
            else -> println("Invalid option entered: $option")
        }
    }while (true)
}


fun addAuthor() {
    val firstName = ScannerInput.readNextLine("Enter authors first name: ")
    val surname = ScannerInput.readNextLine("Enter authors surname: ")
    val biography = ValidateInput.readValidBio("Enter a short biography for author: ")
    val email = ValidateInput.readValidEmail("Enter authors email: ")
    val publisher = ScannerInput.readNextLine("Enter authors publishing company: ")
    val website = ValidateInput.readValidURL("Enter authors website (structure https://www.websitename.com): ")
    val isAdded = authorAPI.add(Author(0,
        firstName = firstName, surname = surname, biography = biography, email = email, publisher = publisher, website = website))

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
}

fun updateAuthor() {
    listAllAuthors()
    if (authorAPI.numberOfAuthors() > 0) {
        // only ask the user to choose the author if author exists
        val id = ScannerInput.readNextInt("Enter the id of the author to update: ")
        if (authorAPI.findAuthor(id) != null) {
            val firstName = ScannerInput.readNextLine("Enter authors first name:")
            val surname = ScannerInput.readNextLine("Enter authors surname: ")
            val biography = ValidateInput.readValidBio("Enter a short biography for author")
            val email = ValidateInput.readValidEmail("Enter authors email:")
            val publisher = ScannerInput.readNextLine("Enter authors publishing company:")
            val website = ValidateInput.readValidURL("Enter authors website:")
            // pass the index of the author and the new author details to AuthorAPI for updating and check for success.
            if (authorAPI.update(id, Author(0, firstName, surname, biography, email, publisher, website))){
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        } else {
            println("There are no authors for this index number")
        }
    }
}

fun deleteAuthor(){
    listAllAuthors()

    if (authorAPI.numberOfAuthors() > 0) {
        // only ask the user to choose the authornto delete if authors exist
        val id = ScannerInput.readNextInt("Enter the id of the author to delete: ")
        // pass the index of the author to AuthorAPI for deleting and check for success.
        val authorToDelete = authorAPI.delete(id)
        if (authorToDelete) {
            println("Delete Successful!")
        } else {
            println("Delete NOT Successful")
        }
    }
}

fun listAllAuthors() = println(authorAPI.listAllAuthors())

fun countAllAuthors() = println(authorAPI.numberOfAuthors())

private fun addBook(){
    val author: Author? = askUserToChooseAuthor()
    if (author != null) {
        if (author.addBook(Book(
                bookTitle = ScannerInput.readNextLine("\t Enter book title: "),
                bookRating = ValidateInput.readValidRating("\t Enter book rating(1-5): "),
                bookGenre = ValidateInput.readValidGenre("\t Enter book genre: "),
                bookLength = ScannerInput.readNextInt("\t Enter book length: "),
                bookPace = ValidateInput.readValidPace("\t Enter book pace (slow/medium/fast): "),
                bookProgress = ValidateInput.readValidProgress("\t Enter book progress (to-be read, currently reading, finished reading): ")
                )
            ))
            println("Add Successful!")
        else println("Add NOT Successful")
    }
}

fun deleteBook(){
    val author: Author? = askUserToChooseAuthor()
    if (author != null) {
        val book: Book? = askUserToChooseBook(author)
        if (book != null) {
            val isDeleted = author.delete(book.bookID)
            if (isDeleted) {
                println("Delete Successful!")
            } else {
                println("Delete NOT Successful")
            }
        }
    }
}

fun updateBook(){
    val author: Author? = askUserToChooseAuthor()
    if (author != null) {
        val book: Book? = askUserToChooseBook(author)
        if (book != null) {
            val newTitle = ScannerInput.readNextLine("Enter new book title: ")
            val newRating = ScannerInput.readNextInt("Enter new book rating(1-5): ")
            val newGenre = ValidateInput.readValidGenre("Enter new book genre: ")
            val newPace = ValidateInput.readValidPace("Enter new book pace(slow/medium/fast): ")
            val newLength = ScannerInput.readNextInt("Enter new book length: ")
            val newProgress = ValidateInput.readValidProgress("Update book progress(to-be read, currently reading, finished reading): ")
            if (author.update(book.bookID, Book(
                    bookTitle = newTitle,
                    bookRating = newRating,
                    bookGenre = newGenre,
                    bookPace = newPace,
                    bookLength = newLength,
                    bookProgress = newProgress
                ))) {
                println("Book details updated")
            } else {
                println("Book details NOT updated")
            }
        } else {
            println("Invalid Book Id")
        }
    }
}

fun listAuthorsBooks() {
    val author: Author? = askUserToChooseAuthor()
        if (author != null){
            print(author.listBooks())
        }
    else {
        println("No books here, add some!!")
    }
}
fun exitApp(){
    println( "Bye bye, come back soon 👋")
    println("exitApp() function invoked")
    System.exit(0)
}

private fun askUserToChooseBook(author: Author): Book? {
    if (author.numberOfBooks() > 0) {
        print(author.listBooks())
        return author.findOne(ScannerInput.readNextInt("\nEnter the id of the book: "))
    }
    else{
        println ("No items for chosen author")
        return null
    }
}

private fun askUserToChooseAuthor(): Author? {
    listAllAuthors()
    if (authorAPI.numberOfAuthors() > 0) {
        val author = authorAPI.findAuthor(ScannerInput.readNextInt("\nEnter the id of the author: "))
        if (author != null) {
            return author //chosen author is active
        }
        else {
            println("author id is not valid")
        }
    }
    return null
}

fun markBookAsOwned() {
    val author: Author? = askUserToChooseAuthor()
    if (author != null) {
        val book: Book? = askUserToChooseBook(author)
        if (book != null) {
            var changeStatus = 'X'
            if (book.isBookOwned) {
                changeStatus =
                    ScannerInput.readNextChar("The book is currently owned...do you want to mark it as un-owned?")
                if ((changeStatus == 'Y') ||  (changeStatus == 'y'))
                    book.isBookOwned= false
                println("Book has been marked as un-owned")
            }
            else {
                changeStatus =
                    ScannerInput.readNextChar("The book is currently un-owned...do you want to mark it as owned?")
                if ((changeStatus == 'Y') ||  (changeStatus == 'y'))
                    book.isBookOwned = true
                println("Book has been marked as owned")
            }
        }
    }
}

fun searchMenu() {
    if (authorAPI.numberOfAuthors() > 0) {
        val option = ScannerInput.readNextInt(
            """
                  > -------------------------------------------------
                  > |   1) Search authors by first name             |
                  > |   2) Search authors by email                  |
                  > |   3) Search books by title                    |
                  > |   4) Search books by genre                    |
                  > |   5) Search books by length                   |
                  > -------------------------------------------------
         > ==>> """.trimMargin(">")
        )

        when (option) {
            1 -> searchAuthorsByFirstName()
            2 -> searchAuthorsByEmail()
            3 -> searchBooksByTitle()
            4 -> searchBooksByGenre()
            5 -> searchBooksByLength()
            else -> println("Invalid option entered: " + option)
        }
    } else {
        println("Option Invalid - There are no authors")
    }
}

fun searchAuthorsByFirstName(){
    val searchName = ScannerInput.readNextLine("Enter first name to search by: ")
    val searchResults = authorAPI.searchByName(searchName)
    if (searchResults.isEmpty()) {
        println("No authors found")
    } else {
        println(searchResults)
    }
}

fun searchAuthorsByEmail(){
    val searchEmail = ValidateInput.readValidEmail("Enter email to search by: ")
    val searchResults = authorAPI.searchByEmail(searchEmail)
    if (searchResults.isEmpty()) {
        println("No authors found")
    } else {
        println(searchResults)
    }
}

fun searchBooksByTitle(){
        val searchContents = ScannerInput.readNextLine("Enter the book title to search by: ")
        val searchResults = authorAPI.searchBookByTitle(searchContents)
        if (searchResults.isEmpty()) {
            println("No books found")
        } else {
            println(searchResults)
        }
}

fun searchBooksByGenre(){
    val searchContents = ValidateInput.readValidGenre("Enter the genre to search by: ")
    val searchResults = authorAPI.searchBookByGenre(searchContents)
    if (searchResults.isEmpty()) {
        println("No books found")
    } else {
        println(searchResults)
    }
}

fun searchBooksByLength(){
    val searchContents = ScannerInput.readNextInt("Enter a book length to search by: ")
    val searchResults = authorAPI.searchBookByLength(searchContents)
    if (searchResults.isEmpty()) {
        println("No books found")
    } else {
        println(searchResults)
    }
}


fun listMenu() {
    if (authorAPI.numberOfAuthors() > 0) {
        val option = ScannerInput.readNextInt(
            """
                  > -------------------------------------------------
                  > |   1) List by publisher                        |
                  > |   2) List by surname                          |
                  > -------------------------------------------------
         > ==>> """.trimMargin(">")
        )

        when (option) {
            1 -> listAuthorsByPublisher()
            2 -> listAuthorsBySurname()
            else -> println("Invalid option entered: " + option)
        }
    } else {
        println("Option Invalid - There are no authors")
    }
}
fun listAuthorsByPublisher() {
    val publisher = ScannerInput.readNextLine("Enter publisher to list by: ")
    val searchResults = authorAPI.listAuthorsByPublisher(publisher)
    if (searchResults.isEmpty()) {
        println("No authors found")
    } else {
        println(searchResults)
    }
}

fun listAuthorsBySurname() {
    val surname = ScannerInput.readNextLine("Enter surname to list by: ")
    val searchResults = authorAPI.listAuthorsBySurname(surname)
    if (searchResults.isEmpty()) {
        println("No authors found")
    } else {
        println(searchResults)
    }
}

fun countMenu() {
    if (authorAPI.numberOfAuthors() > 0) {
        val option = ScannerInput.readNextInt(
            """
                  > -------------------------------------------------
                  > |   1) Count all authors                        |
                  > |   2) Count authors by publisher               |
                  > |   3) Count authors by surname                 |
                  > -------------------------------------------------
         > ==>> """.trimMargin(">")
        )

        when (option) {
            1 -> countAllAuthors()
            2 -> countAuthorsByPublisher()
            3 -> countAuthorsBySurname()
            else -> println("Invalid option entered: " + option)
        }
    } else {
        println("Option Invalid - There are no authors")
    }
}

fun countAuthorsByPublisher() {
    val publisher = ScannerInput.readNextLine("Enter publisher to count by: ")
    val searchResults = authorAPI.numberOfAuthorsByPublisher(publisher)
    println(searchResults)
}

fun countAuthorsBySurname() {
    val surname = ScannerInput.readNextLine("Enter surnmae to count by: ")
    val searchResults = authorAPI.numberOfAuthorsBySurname(surname)
    println(searchResults)
}
