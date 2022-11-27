import controllers.AuthorAPI
import models.Author
import models.Book
import persistence.JSONSerializer
import utils.ScannerInput
import java.io.File

private val authorAPI = AuthorAPI(JSONSerializer(File("notes.json")))

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
          > |   8) List all books            |
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
            0 -> exitApp()
            else -> println("Invalid option entered: $option")
        }
    }while (true)
}

fun addAuthor() {
    val firstName = ScannerInput.readNextLine("Enter authors first name:")
    val surname = ScannerInput.readNextLine("Enter authors surname: ")
    val biography = ScannerInput.readNextLine("Enter a short biography for author")
    val email = ScannerInput.readNextLine("Enter authors email:")
    val publisher = ScannerInput.readNextLine("Enter authors publishing company:")
    val website = ScannerInput.readNextLine("Enter authors website:")
    val isAdded = authorAPI.add(Author(0,firstName = firstName, surname = surname, biography = biography, email = email, publisher = publisher, website = website))

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
}

fun updateAuthor() {
    listAllAuthors()
    if (authorAPI.numberOfAuthors() > 0) {
        // only ask the user to choose the note if notes exist
        val id = ScannerInput.readNextInt("Enter the id of the note to update: ")
        if (authorAPI.findAuthor(id) != null) {
            val firstName = ScannerInput.readNextLine("Enter authors first name:")
            val surname = ScannerInput.readNextLine("Enter authors surname: ")
            val biography = ScannerInput.readNextLine("Enter a short biography for author")
            val email = ScannerInput.readNextLine("Enter authors email:")
            val publisher = ScannerInput.readNextLine("Enter authors publishing company:")
            val website = ScannerInput.readNextLine("Enter authors website:")
            // pass the index of the note and the new note details to NoteAPI for updating and check for success.
            if (authorAPI.update(id, Author(0, firstName, surname, biography, email, publisher, website))){
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        } else {
            println("There are no notes for this index number")
        }
    }
}

fun deleteAuthor(){
    listAllAuthors()

    if (authorAPI.numberOfAuthors() > 0) {
        // only ask the user to choose the note to delete if notes exist
        val id = ScannerInput.readNextInt("Enter the id of the note to delete: ")
        // pass the index of the note to NoteAPI for deleting and check for success.
        val noteToDelete = authorAPI.delete(id)
        if (noteToDelete) {
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
                bookRating = ScannerInput.readNextInt("\t Enter book rating: "),
                bookGenre = ScannerInput.readNextLine("\t Enter book genre: "),
                bookLength = ScannerInput.readNextInt("\t Enter book length: "),
                bookPace = ScannerInput.readNextLine("\t Enter book pace (slow/medium/fast): "),
                bookProgress = ScannerInput.readNextLine("\t Enter book progress (to-be-read, currently reading, finished reading): ")
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
            val newRating = ScannerInput.readNextInt("Enter new book rating: ")
            val newGenre = ScannerInput.readNextLine("Enter new book genre: ")
            val newPace = ScannerInput.readNextLine("Enter new book pace: ")
            val newLength = ScannerInput.readNextInt("Enter new book length: ")
            val newProgress = ScannerInput.readNextLine("Update book progress: ")
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
        val author = authorAPI.findAuthor(ScannerInput.readNextInt("\nEnter the id of the note: "))
        if (author != null) {
            return author //chosen note is active
        }
        else {
            println("Note id is not valid")
        }
    }
    return null
}

//selected note is not active

