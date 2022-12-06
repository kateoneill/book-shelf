package utils

object ProgressValidation {
    /**
     * creating set of progress options
     */
    @JvmStatic
    val progressOptions = setOf("To-be read", "Currently Reading", "Finished")

    /**
     * checking if progress entered is in set above
     */
    @JvmStatic
    fun isValidProgress(progressToCheck: String?): Boolean {
        for (dueDate in progressOptions) {
            if (dueDate.equals(progressToCheck, ignoreCase = true)) {
                return true
            }
        }
        return false
    }
}
