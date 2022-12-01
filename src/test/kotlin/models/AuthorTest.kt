package models

import controllers.AuthorAPI
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.XMLSerializer
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AuthorTest {
    private var NeverGo : Book? = null
    private var SallyRooney : Author? = null
    private var kazuoIshiguro : Author? = null
    private var RemainsDay: Book? = null
    private var ArtistWorld: Book? = null
    private var BuriedGiant: Book? = null
    private var PaleView: Book? = null
    private var KlaraSun: Book? = null

    @BeforeEach
    fun setup() {

        NeverGo = Book(0, "Never let me go", 5, "Literary fiction", "medium", false, 120, "currently reading")
        RemainsDay = Book(0, "The Remains of the Day", 3, "Romance", "medium", false, 300,"to-be read")
        ArtistWorld = Book(0, "An artist if the floating world",4, "Mystery", "fast", false, 200,"finished")
        BuriedGiant = Book(0, "The buried giant", 5, "Thriller", "slow", false, 250,"currently reading")
        PaleView = Book(0, "A pale view of hills",2, "YA", "slow", true, 700,"finished")
        KlaraSun = Book(0, "Klara and the sun", 3, "Sci-fi", "slow", false, 125, "currently reading")
        kazuoIshiguro =  Author(0, "Kazuo", "Ishiguro", "Sir Kazuo Ishiguro OBE FRSA FRSL is a British novelist, screenwriter, musician, and short-story writer", "kishiguro@email.com", "Faber & Faber", "kazuoishiguro.com",
            mutableSetOf<Book>())
        SallyRooney = Author(0, "Sally", "Rooney", "Sally Rooney is an Irish author and screenwriter.", "srooney@email.com", "Penguin", "sally.com", mutableSetOf<Book>())


        // adding 5 books
        kazuoIshiguro!!.addBook(NeverGo!!)
        kazuoIshiguro!!.addBook(RemainsDay!!)
        kazuoIshiguro!!.addBook(ArtistWorld!!)
        kazuoIshiguro!!.addBook(BuriedGiant!!)
        kazuoIshiguro!!.addBook(PaleView!!)
        kazuoIshiguro!!.addBook(KlaraSun!!)
    }

    @AfterEach
    fun tearDown() {
        NeverGo = null
        kazuoIshiguro = null
        ArtistWorld = null
        BuriedGiant = null
        RemainsDay = null
        PaleView = null
        KlaraSun = null
    }

    @Nested
    inner class AddBooks {
        @Test
        fun `adding a Book to a populated Author adds to book ArrayList`() {
            val newBook = Book(0, "The Unconsoled", 3, "Romance", "medium", false, 300,"to-be read")
            assertEquals(6, kazuoIshiguro!!.numberOfBooks())
            assertTrue(kazuoIshiguro!!.addBook(newBook))
            assertEquals(7, kazuoIshiguro!!.numberOfBooks())
            assertEquals(newBook, kazuoIshiguro!!.findOne(kazuoIshiguro!!.numberOfBooks() - 1))
        }

        @Test
        fun `adding a Book to an empty author adds to book ArrayList`() {
            val newBook = Book(0, "The Unconsoled", 3, "Romance", "medium", false, 300,"to-be read")
            assertEquals(0, SallyRooney!!.numberOfBooks())
            assertTrue(SallyRooney!!.addBook(newBook))
            assertEquals(1, SallyRooney!!.numberOfBooks())
            assertEquals(newBook, SallyRooney!!.findOne(SallyRooney!!.numberOfBooks() - 1))
        }
    }
}