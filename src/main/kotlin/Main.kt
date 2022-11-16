import utils.ScannerInput

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
            2 -> listAuthors()
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
    println("Author added")
}

fun updateAuthor(){
    println("Updating author")
}

fun deleteAuthor(){
    println("deleting author")
}

fun listAuthors(){
    println("list all authors")
}

fun addBook(){
    println("Add book")
}

fun deleteBook(){
    println("delete book")
}

fun updateBook(){
    println("update book")
}
fun exitApp(){
    println( "Bye bye, come back soon 👋")
    println("exitApp() function invoked")
    System.exit(0)
}