package utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UtilititesTest {
    @Test
    fun validRangeWorksWithPositiveTestData() {
        Assertions.assertTrue(Utilities.validRange(1, 1, 1))
        Assertions.assertTrue(Utilities.validRange(1, 1, 2))
        Assertions.assertTrue(Utilities.validRange(1, 0, 1))
        Assertions.assertTrue(Utilities.validRange(1, 0, 2))
        Assertions.assertTrue(Utilities.validRange(-1, -2, -1))
    }

    @Test
    fun validRangeWorksWithNegativeTestData() {
        Assertions.assertFalse(Utilities.validRange(1, 0, 0))
        Assertions.assertFalse(Utilities.validRange(1, 1, 0))
        Assertions.assertFalse(Utilities.validRange(1, 2, 1))
        Assertions.assertFalse(Utilities.validRange(-1, -1, -2))
    }

    @Test
    fun validIDWorksWithPositiveTestData() {
        Assertions.assertTrue(Utilities.isValidID(1, 1, 1))
        Assertions.assertTrue(Utilities.isValidID(1, 1, 2))
        Assertions.assertTrue(Utilities.isValidID(1, 0, 1))
        Assertions.assertTrue(Utilities.isValidID(1, 0, 2))
        Assertions.assertTrue(Utilities.isValidID(-1, -2, -1))
    }

    @Test
    fun validIXWorksWithNegativeTestData() {
        Assertions.assertFalse(Utilities.isValidID(1, 0, 0))
        Assertions.assertFalse(Utilities.isValidID(1, 1, 0))
        Assertions.assertFalse(Utilities.isValidID(1, 2, 1))
        Assertions.assertFalse(Utilities.isValidID(-1, -1, -2))
    }
}