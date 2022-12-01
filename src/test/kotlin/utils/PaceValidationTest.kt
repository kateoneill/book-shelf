package utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.PaceValidation.isValidPace
import utils.PaceValidation.paceOptions

class PaceValidationTest {
    @Test
    fun ReturnsFullPaceSet() {
        Assertions.assertEquals(3, paceOptions.size)
        Assertions.assertTrue(paceOptions.contains("Slow"))
        Assertions.assertTrue(paceOptions.contains("Medium"))
        Assertions.assertTrue(paceOptions.contains("Fast"))
        Assertions.assertFalse(paceOptions.contains(""))
    }

    @Test
    fun isValidPaceTrueWhenPaceExists() {
        Assertions.assertTrue(isValidPace("SLOW"))
        Assertions.assertTrue(isValidPace("meDiUm"))
        Assertions.assertTrue(isValidPace("Fast"))
        Assertions.assertTrue(isValidPace("slow"))
    }

    @Test
    fun isValidPaceFalseWhenPaceDoesNotExist() {
        Assertions.assertFalse(isValidPace("slo"))
        Assertions.assertFalse(isValidPace("edium"))
        Assertions.assertFalse(isValidPace("fasttt"))
        Assertions.assertFalse(isValidPace("sllooow"))
        Assertions.assertFalse(isValidPace(""))
    }
}