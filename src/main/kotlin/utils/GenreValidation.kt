package utils

object GenreValidation {
    @JvmStatic
    val genreOptions = setOf("Horror", "Action", "Fantasy", "Auto-biography", "Thriller", "Literary Fiction", "Memoir", "Self-help", "Spirituality", "Sci-fi", "Romance", "Contemporary", "YA", "Children", "Graphic Novel", "Mystery")

    @JvmStatic
    fun isValidGenre(genreToCheck: String?): Boolean {
        for (dueDate in genreOptions) {
            if (dueDate.equals(genreToCheck, ignoreCase = true)) {
                return true
            }
        }
        return false
    }
}
