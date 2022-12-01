package utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ProgressValidationTest {
    @Test
    fun ReturnsFullProgressSet() {
        Assertions.assertEquals(3, ProgressValidation.progressOptions.size)
        Assertions.assertTrue(ProgressValidation.progressOptions.contains("Currently Reading"))
        Assertions.assertTrue(ProgressValidation.progressOptions.contains("To-be read"))
        Assertions.assertTrue(ProgressValidation.progressOptions.contains("Finished"))
        Assertions.assertFalse(ProgressValidation.progressOptions.contains(""))
    }

    @Test
    fun isValidProgressTrueWhenProgressExists() {
        Assertions.assertTrue(ProgressValidation.isValidProgress("CURRENTLY READING"))
        Assertions.assertTrue(ProgressValidation.isValidProgress("to-be read"))
        Assertions.assertTrue(ProgressValidation.isValidProgress("TO-bE REad"))
        Assertions.assertTrue(ProgressValidation.isValidProgress("finisHeD"))
    }

    @Test
    fun isValidProgressFalseWhenProgressDoesNotExist() {
        Assertions.assertFalse(ProgressValidation.isValidProgress("currentlyreading"))
        Assertions.assertFalse(ProgressValidation.isValidProgress("inished"))
        Assertions.assertFalse(ProgressValidation.isValidProgress("tobe read"))
        Assertions.assertFalse(ProgressValidation.isValidProgress("currentlyy readingg"))
        Assertions.assertFalse(ProgressValidation.isValidProgress(""))
    }
}