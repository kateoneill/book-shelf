package utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class URLUtilityClass {
    @Test
    fun `when URL fits regex standard return true`() {
        Assertions.assertTrue(URLValidation.isValidURL("http://www.website.com"))
        Assertions.assertTrue(URLValidation.isValidURL("https://www.web-site.co.uk"))
        Assertions.assertTrue(URLValidation.isValidURL("https://www.web&site.com"))
        Assertions.assertTrue(URLValidation.isValidURL("https://www.web#site.com"))
        Assertions.assertTrue(URLValidation.isValidURL("https://www.%site.com"))
        Assertions.assertTrue(URLValidation.isValidURL("https://www.?site.com"))
        Assertions.assertTrue(URLValidation.isValidURL("https://www.~site.com"))
    }

    @Test
    fun `when URL does not comply with regex standard return false`() {
        Assertions.assertFalse(URLValidation.isValidURL("website"))
        Assertions.assertFalse(URLValidation.isValidURL("website.com"))
        Assertions.assertFalse(URLValidation.isValidURL("www.website.com"))
        Assertions.assertFalse(URLValidation.isValidURL("htt://www.website.com"))
        Assertions.assertFalse(URLValidation.isValidURL("http://www.web'site.com"))
        Assertions.assertFalse(URLValidation.isValidURL("http://www.web^site.com"))
        Assertions.assertFalse(URLValidation.isValidURL("http://www.web*site.com"))
    }
}
