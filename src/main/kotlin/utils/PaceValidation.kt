package utils

object PaceValidation {
    /**
     * setting up set of pace options for validation
     */
    @JvmStatic
    val paceOptions = setOf("Slow", "Medium", "Fast")

    /**
     * checking if pace is in set above
     */
    @JvmStatic
    fun isValidPace(paceToCheck: String?): Boolean {
        for (dueDate in paceOptions) {
            if (dueDate.equals(paceToCheck, ignoreCase = true)) {
                return true
            }
        }
        return false
    }
}
