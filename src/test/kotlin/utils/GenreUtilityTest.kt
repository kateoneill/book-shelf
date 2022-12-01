package utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.GenreValidation.genreOptions
import utils.GenreValidation.isValidGenre

class GenreUtilityTest {
    @Test
    fun genresReturnsFullGenreSet() {
        Assertions.assertEquals(16, genreOptions.size)
        Assertions.assertTrue(genreOptions.contains("Horror"))
        Assertions.assertTrue(genreOptions.contains("Action"))
        Assertions.assertTrue(genreOptions.contains("Fantasy"))
        Assertions.assertTrue(genreOptions.contains("Auto-biography"))
        Assertions.assertTrue(genreOptions.contains("Thriller"))
        Assertions.assertTrue(genreOptions.contains("Literary Fiction"))
        Assertions.assertTrue(genreOptions.contains("Memoir"))
        Assertions.assertTrue(genreOptions.contains("Self-help"))
        Assertions.assertTrue(genreOptions.contains("Spirituality"))
        Assertions.assertTrue(genreOptions.contains("Sci-fi"))
        Assertions.assertTrue(genreOptions.contains("Romance"))
        Assertions.assertTrue(genreOptions.contains("Contemporary"))
        Assertions.assertTrue(genreOptions.contains("YA"))
        Assertions.assertTrue(genreOptions.contains("Children"))
        Assertions.assertTrue(genreOptions.contains("Graphic Novel"))
        Assertions.assertTrue(genreOptions.contains("Mystery"))
        Assertions.assertFalse(genreOptions.contains(""))
    }

    @Test
    fun isValidCategoryTrueWhenGenreExists() {
        Assertions.assertTrue(isValidGenre("Horror"))
        Assertions.assertTrue(isValidGenre("horror"))
        Assertions.assertTrue(isValidGenre("MYSTERY"))
        Assertions.assertTrue(isValidGenre("GraPhIc NoVeL"))
        Assertions.assertTrue(isValidGenre("SElf-hELP"))
        Assertions.assertTrue(isValidGenre("romancE"))
    }

    @Test
    fun isValidCategoryFalseWhenGenreDoesNotExist() {
        Assertions.assertFalse(isValidGenre("myster"))
        Assertions.assertFalse(isValidGenre("contemmporarry"))
        Assertions.assertFalse(isValidGenre("omance"))
        Assertions.assertFalse(isValidGenre("childre"))
        Assertions.assertFalse(isValidGenre(""))
    }
}