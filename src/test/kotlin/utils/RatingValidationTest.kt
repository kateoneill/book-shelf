package utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RatingValidationTest {
    @Test
    fun returnsTrueIfRatingIsInRange() {
        Assertions.assertTrue(RatingValidation.isValidRating(1))
        Assertions.assertTrue(RatingValidation.isValidRating(2))
        Assertions.assertTrue(RatingValidation.isValidRating(3))
        Assertions.assertTrue(RatingValidation.isValidRating(4))
        Assertions.assertTrue(RatingValidation.isValidRating(5))
    }

    @Test
    fun returnsFalseIfRatingIsNotInRange() {
        Assertions.assertFalse(RatingValidation.isValidRating(0))
        Assertions.assertFalse(RatingValidation.isValidRating(-1))
        Assertions.assertFalse(RatingValidation.isValidRating(6))
        Assertions.assertFalse(RatingValidation.isValidRating(10))
    }
}
