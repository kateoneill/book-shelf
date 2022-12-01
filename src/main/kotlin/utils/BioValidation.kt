package utils

object BioValidation {
    @JvmStatic
    fun isValidBio(bio: String?): Boolean {
        val maxLength = 100
        val bioLength = bio?.length

        if (maxLength >= bioLength!!) {
            return true
        }
        return false
    }
}