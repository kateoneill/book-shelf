package utils

object RatingValidation {
    // https://kotlinlang.org/docs/ranges.html#progression
    /**
     * checking if rating entered is between 1 and 5
     */
    @JvmStatic
    fun isValidRating(RatingToCheck: Int?): Boolean {
        if (RatingToCheck!! in (1..5)) {
            return true
        }
        return false
    }
}
