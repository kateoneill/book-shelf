package utils

object BioValidation {
    // https://www.educative.io/answers/how-to-find-the-length-of-a-string-in-java
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
