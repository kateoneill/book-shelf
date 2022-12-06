package utils

import java.util.regex.Matcher
import java.util.regex.Pattern

object EmailValidation {
    @JvmStatic
    fun isValidEmail(email: String?): Boolean {
        // This utility method uses the regex library.
        // email validation regex gotten from https://www.baeldung.com/java-email-validation-regex
        val regex = (
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" +
                "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
            )

        // Compile the ReGex
        val p: Pattern = Pattern.compile(regex)
        val m: Matcher = p.matcher(email)
        return m.matches()
    }
}
