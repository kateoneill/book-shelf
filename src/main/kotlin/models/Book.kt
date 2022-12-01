package models

import persistence.Serializer

data class Book(
    var bookID: Int = 0,
    var bookTitle: String,
    var bookRating: Int,
    var bookGenre: String,
    var bookPace: String,
    var isBookOwned: Boolean = false,
    var bookLength: Int,
    var bookProgress: String ){

}