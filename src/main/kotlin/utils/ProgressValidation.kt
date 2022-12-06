package utils

object ProgressValidation {
    @JvmStatic
    val progressOptions = setOf("To-be read", "Currently Reading", "Finished")

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
