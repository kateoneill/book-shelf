package models

data class Book(
    var bookID: Int = 0,
    var bookTitle: String,
    var bookRating: Int,
    var bookGenre: String,
    var bookPace: String,
    var isBookOwned: Boolean = false,
    var bookLength: Int,
    var bookProgress: String
) {
    override fun toString(): String {
        return "Book #${bookID}" +
                "\n Title: ${bookTitle}(${bookRating}*)   ${bookLength} pages" +
                "\n Genre: ${bookGenre}  Pace:${bookPace}" +
                "\n Is book owned: ${isBookOwned} " +
                "\n This book is ${bookProgress}" +
                "\n ~~~~~~~~~~~~~~~~~~~~~~~~~"
    }
}
