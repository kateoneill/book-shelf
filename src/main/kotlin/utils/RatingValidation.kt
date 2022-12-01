package utils

object RatingValidation {
    @JvmStatic
    fun isValidRating(RatingToCheck: Int?): Boolean{
        if(RatingToCheck!! in (1..5 )){
            return true
        }
        return false
    }
}