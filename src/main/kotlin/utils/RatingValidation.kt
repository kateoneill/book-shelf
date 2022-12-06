package utils

object RatingValidation {
    // https://kotlinlang.org/docs/ranges.html#progression
    @JvmStatic
    fun isValidRating(RatingToCheck: Int?): Boolean {
        if (RatingToCheck!! in (1..5)) {
            return true
        }
        return false
    }
}
