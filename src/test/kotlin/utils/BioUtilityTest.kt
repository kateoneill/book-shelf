package utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BioUtilityTest {
    @Test
    fun `when biography is less than 100 chars return true`() {
        Assertions.assertTrue(BioValidation.isValidBio("hello this is a test"))
        Assertions.assertTrue(BioValidation.isValidBio("HELLO THIS IS A TEST "))
        Assertions.assertTrue(BioValidation.isValidBio("hello this is a test to test the string length so this string is 99 characters in length to be sure"))
    }

    @Test
    fun `when biography is more than 100 chars return false`() {
        Assertions.assertFalse(BioValidation.isValidBio("hello this is a test to check the string length so this string is 101 characters in length to be sure"))
        Assertions.assertFalse(BioValidation.isValidBio("hello this is a test to check the string length so this string is over the CHARACTERS in length to be sure"))
    }
}
