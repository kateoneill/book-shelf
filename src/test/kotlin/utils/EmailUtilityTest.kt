package utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.EmailValidation.isValidEmail

class EmailUtilityTest {
    @Test
    fun `when email fits regex standard return true`() {
        Assertions.assertTrue(isValidEmail("email@email.com"))
        Assertions.assertTrue(isValidEmail("EmAIL267@emailSS.ie"))
        Assertions.assertTrue(isValidEmail("127e-mail@mailp.co.uk"))
    }

    @Test
    fun `when email does not comply with regex standard return false`() {
        Assertions.assertFalse(isValidEmail("e..mail@email.com"))
        Assertions.assertFalse(isValidEmail("e-mail++@emails"))
        Assertions.assertFalse(isValidEmail("sarah"))
    }
}
