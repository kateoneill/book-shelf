package utils

object PaceValidation {
        @JvmStatic
        val paceOptions = setOf("Slow", "Medium", "Fast")

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
