package utils

import java.util.regex.Matcher
import java.util.regex.Pattern

object URLValidation {

    /**
     * checking if URL is valid and fits regex
     */
    @JvmStatic
    fun isValidURL(url: String?): Boolean {
        // This utility method uses the regex library.
        val regex = (
            "((http|https)://)(www.)?" +
                "[a-zA-Z0-9@:%._\\+~#?&//=]" +
                "{2,256}\\.[a-z]" +
                "{2,6}\\b([-a-zA-Z0-9@:%" +
                "._\\+~#?&//=]*)"
            )

        // Compile the ReGex
        val p: Pattern = Pattern.compile(regex)
        val m: Matcher = p.matcher(url)
        return m.matches()
    }
}
