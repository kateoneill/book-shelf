import controllers.AuthorAPI
import models.Author
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

fun addBook(){
    println("Add book")
}

fun deleteBook(){
    println("deleting book")
}

fun updateBook(){
    println("update book")
}
fun exitApp(){
    println( "Bye bye, come back soon 👋")
    println("exitApp() function invoked")
    System.exit(0)
}