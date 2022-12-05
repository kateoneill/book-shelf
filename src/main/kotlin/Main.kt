import controllers.AuthorAPI
import models.Author
import models.Book
import persistence.JSONSerializer
import utils.ScannerInput
import java.io.File
import utils.ValidateInput

private val authorAPI = AuthorAPI(JSONSerializer(File("authors.json")))

fun main(args: Array<String>) {
    entryScreen();
}

fun entryScreen() {
    do {
        val option = entryscreendisplay()
        when (option) {
            1 -> runMenu()
            else -> runMenu()
        }
    } while (true)
}
fun entryscreendisplay(): Int {
    return ScannerInput.readNextInt("""
      >        .---.               .---.        
      >    .---|---|   .---.       |   |   .---.___ 
      > .--|===|   |---|___|.--.___|   |---|:::|   |--.
      > |  | B | O | O | K | - | S | H | E | L | F |  |
      > |  |   |   |===|   |===|   |   |   |:::|   |  |
      > |  |   |   |   |___|___|   |   |___|   |   |  |
      > |~~|===|---|===|~~~|~~~|%%%|   |---|:::|~~~|  |
      > ^--^---'---^---^---^---^---^---'--_^---^---^--^
      >    Press a number to delve into the books!
      >    ->>  """.trimMargin(">"))
}

fun mainMenu() : Int {
    return ScannerInput.readNextInt("""
          >       ___              __              __         ______
          >     / __ )____  ____  / /__      _____/ /_  ___  / / __/
          >    / __  / __ \/ __ \/ //_/_____/ ___/ __ \/ _ \/ / /_  
          >   / /_/ / /_/ / /_/ / ,< /_____(__  ) / / /  __/ / __/  
          >  /_____/\____/\____/_/|_|     /____/_/ /_/\___/_/_/     
          >                                                      
          >                 _____________________________
          >                /                            /  ,
          >               /    TABLE OF CONTENTS       /  /
          >              /   AUTHOR                   /  /
          >             /     1. ADD AUTHOR          /  /
          >            /     2. UPDATE AUTHOR       /  /
          >           /     3. DELETE AUTHOR       /  /
          >          /    BOOK                    /  /
          >         /      4. ADD BOOK           /  /
          >        /      5. UPDATE BOOK        /  /
          >       /      6. DELETE BOOK        /  /
          >      /      7. MARK OWN BOOK      /  /
          >     /     Other                  /  /
          >    /      8. Functions menu     /  /
          >   /____________________________/  /
          >  (____________________________(  /
                      
          > Choose your chapter:""".trimMargin(">"))
}


fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1 -> addAuthor()
            2 -> updateAuthor()
            3 -> deleteAuthor()
            4 -> addBook()
            5 -> updateBook()
            6 -> deleteBook()
            7 -> markBookAsOwned()
            8 -> functionsMenu()
            0 -> exitApp()
            else -> println("Invalid option entered: $option")
        }
    }while (true)
}

fun functionsMenu() {
    if (authorAPI.numberOfAuthors() > 0) {
        val option = ScannerInput.readNextInt(
            """
                  >                            Press 1 to turn page ->
                  >        __________________   __________________
                  >    .-/|                  \ /                  |\-.
                  >    ||||   Search Menu     |    List Menu      ||||
                  >    ||||                   |                   ||||
                  >    ||||   Search by :     |   List:           ||||
                  >    ||||   2. First name   |   7. Authors      ||||
                  >    ||||   3. Email        |   8. Author books ||||
                  >    ||||   4. Book Title   |   9. By publisher ||||
                  >    ||||   5. Book Genre   |   10. By surname  ||||
                  >    ||||   6. Book Length  |   11. Owned books ||||
                  >    ||||                   |   12. By Length   ||||
                  >    ||||                   |   13. By Rating   ||||
                  >    ||||                   |                   ||||
                  >    ||||__________________ | __________________||||
                  >    ||/===================\|/===================\||
                  >    `--------------------~___~-------------------''
         > Choose your chapter: """.trimMargin(">")
        )

        when (option) {
            1 -> functionPage2()
            2 -> searchAuthorsByFirstName()
            3 -> searchAuthorsByEmail()
            4 -> searchBooksByTitle()
            5 -> searchBooksByGenre()
            6 -> searchBooksByLength()
            7 -> listAllAuthors()
            8 -> listAuthorsBooks()
            9 -> listAuthorsByPublisher()
            10 -> listAuthorsBySurname()
            11 -> ListBooksThatAreOwned()
            12 -> listBooksByLength()
            13 -> listBooksByRating()
            else -> println("Invalid option entered: " + option)
        }
    } else {
        println("Option Invalid - There are no authors")
    }
}

fun functionPage2() {
    if (authorAPI.numberOfAuthors() > 0) {
        val option = ScannerInput.readNextInt(
            """
                  > <- Press 1 to turn back
                  >        __________________   __________________
                  >    .-/|                  \ /                  |\-.
                  >    ||||   Count Menu      |    Miscellaneous  ||||
                  >    ||||                   |                   ||||
                  >    ||||   Count :         |                   ||||
                  >    ||||   2. All authors  |  5. Author        ||||
                  >    ||||   3. By publisher |        dashboard  ||||
                  >    ||||   4. By Surname   |                   ||||
                  >    ||||                   |                   ||||
                  >    ||||                   |                   ||||
                  >    ||||                   |                   ||||
                  >    ||||                   |                   ||||
                  >    ||||                   |                   ||||
                  >    ||||                   |                   ||||
                  >    ||||__________________ | __________________||||
                  >    ||/===================\|/===================\||
                  >    `--------------------~___~-------------------''
         > Choose your chapter: """.trimMargin(">")
        )

        when (option) {
            1 -> functionsMenu()
            2 -> countAllAuthors()
            3 -> countAuthorsByPublisher()
            4 -> countAuthorsBySurname()
            5 -> getAuthorDashboard()
            else -> println("Invalid option entered: " + option)
        }
    } else {
        println("Option Invalid - There are no authors")
    }
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

fun ListBooksThatAreOwned() {
    if (authorAPI.numberOfBooksMarkedOwned() > 0) {
        println("Total books owned: ${authorAPI.numberOfBooksMarkedOwned()}")
    }
    println(authorAPI.listBooksMarkedOwned())
}

fun listBooksByLength() {
    val author: Author? = askUserToChooseAuthor()
    if (author!!.numberOfBooks() > 0){
        print(author.listBooksInOrderOfPageLength())
    }
    else {
        println("No books here, add some!!")
    }
}

fun listBooksByRating() {
    val author: Author? = askUserToChooseAuthor()
    if (author!!.numberOfBooks() > 0){
        print(author.listBooksByRating())
    }
    else {
        println("No books here, add some!!")
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

fun getAuthorDashboard() {
    val author: Author? = askUserToChooseAuthor()
    if (author != null) {
        print(author.authorDashboard())
    } else {
        println("No books here, add some!!")
    }
}

