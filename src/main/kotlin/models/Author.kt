package models

data class Author (
    var authorID: Int = 0,
    var firstName: String,
    var surname: String,
    var biography: String,
    var email: String,
    var publisher: String,
    var website: String,
    var books: MutableSet<Book> = mutableSetOf()
        ){

    private var lastItemId = 0
    private fun getItemId() = lastItemId++

}