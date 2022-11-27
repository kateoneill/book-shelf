package controllers

import models.Author
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.XMLSerializer
import java.io.File
import javax.swing.plaf.synth.SynthTextAreaUI
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class AuthorAPITest {
    private var kazuoIshiguro: Author? = null
    private var SallyRooney: Author? = null
    private var TaylorJenkinsReid: Author? = null
    private var StephenKing: Author? = null
    private var akwaekeEmezi: Author? = null
    private var ColsonWhitehead: Author? = null
    private var populatedAuthors: AuthorAPI? = AuthorAPI(XMLSerializer(File("notes.xml")))
    private var emptyAuthors: AuthorAPI? = AuthorAPI(XMLSerializer(File("notes.xml")))

    @BeforeEach
    fun setup() {
        kazuoIshiguro = Author(0, "Kazuo", "Ishiguro", "Sir Kazuo Ishiguro OBE FRSA FRSL is a British novelist, screenwriter, musician, and short-story writer", "kishiguro@email.com", "Faber & Faber", "kazuoishiguro.com")
        SallyRooney = Author(0, "Sally", "Rooney", "Sally Rooney is an Irish author and screenwriter.", "srooney@email.com", "Penguin", "sally.com")
        TaylorJenkinsReid = Author(0, "Taylor","Jenkins Reid", "Taylor Jenkins Reid is an American author most known for her novels The Seven Husbands of Evelyn Hugo, Daisy Jones & The Six, and Malibu Rising.", "tjreidbooks@email.com", "Penguin", "taylorjenkinsreid.com")
        StephenKing = Author(0, "Stephen", "King", "Stephen Edwin King is an American author of horror, supernatural fiction, suspense, crime, science-fiction, and fantasy novels", "sking@email.com", "Simon & Schuster", "stephenking.com")
        akwaekeEmezi = Author(0, "Akwaeke","Emezi", "Akwaeke Emezi is a Nigerian fiction writer and video artist", "aemeziauthor@email.com", "Simon & Schuster", "sallyrooney.com")
        ColsonWhitehead = Author(0, "Colson", "Whitehead", "Arch Colson Chipp Whitehead is an American novelist. He is the author of eight novels.", "cwhitehead@email.com", "Little, Brown and Company", "colsonwhitehead.com")

        // adding 5 Note to the notes api
        populatedAuthors!!.add(kazuoIshiguro!!)
        populatedAuthors!!.add(SallyRooney!!)
        populatedAuthors!!.add(TaylorJenkinsReid!!)
        populatedAuthors!!.add(StephenKing!!)
        populatedAuthors!!.add(akwaekeEmezi!!)
        populatedAuthors!!.add(ColsonWhitehead!!)
    }

    @AfterEach
    fun tearDown() {
        kazuoIshiguro = null
        SallyRooney = null
        TaylorJenkinsReid = null
        StephenKing = null
        akwaekeEmezi = null
        ColsonWhitehead = null
        emptyAuthors = null
    }

    @Nested
    inner class AddAuthors {
        @Test
        fun `adding an Author to a populated list adds to ArrayList`() {
            val newAuthor = Author(7, "Brit", "Bennett", "Brit Bennett is an American writer based in Los Angeles.", "bbennett@email.com", "Riverhead Books", "britbennett.com")
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            assertTrue(populatedAuthors!!.add(newAuthor))
            assertEquals(7, populatedAuthors!!.numberOfAuthors())
            assertEquals(newAuthor, populatedAuthors!!.findAuthor(populatedAuthors!!.numberOfAuthors() - 1))
        }

        @Test
        fun `adding an Author to an empty list adds to ArrayList`() {
            val newAuthor = Author(8, "Douglas", "Stuart", "Douglas Stuart is a Scottish-American writer and fashion designer", "dstuart@email.com", "Picador", "DouglasStuart.com")
            assertEquals(0, emptyAuthors!!.numberOfAuthors())
            assertTrue(emptyAuthors!!.add(newAuthor))
            assertEquals(1, emptyAuthors!!.numberOfAuthors())
            assertEquals(newAuthor, emptyAuthors!!.findAuthor(emptyAuthors!!.numberOfAuthors() - 1))
        }
    }

    @Nested
    inner class UpdateAuthors {
        @Test
        fun `updating a note that does not exist returns false`() {
            assertFalse(populatedAuthors!!.update(6, Author(0, "Maria", "Morgan", "Maria Morgan is from Wales", "mmorgan@email.com", "Simons", "mmorgan.co.uk")))
            assertFalse(populatedAuthors!!.update(-1, Author(0, "Tom", "Johns", "T.J is an established sci-fi writer", "tj.books@email.com", "Dead Books", "tjbooks.com")))
            assertFalse(emptyAuthors!!.update(0, Author(0, "Rua", "Lee", "Rua is a childrens fiction writer from Spain", "rlee@gmail.com", "Red herring ", "rua.com")))
        }

        @Test
        fun `updating a note that exists returns true and updates`() {
            // check note 5 exists and check the contents
            assertEquals(akwaekeEmezi, populatedAuthors!!.findAuthor(4))
            assertEquals("aemeziauthor@email.com", populatedAuthors!!.findAuthor(4)!!.email)
            assertEquals("Simon & Schuster", populatedAuthors!!.findAuthor(4)!!.publisher)
            assertEquals("sallyrooney.com", populatedAuthors!!.findAuthor(4)!!.website)

            // update note 5 with new information and ensure contents updated successfully
            assertTrue(populatedAuthors!!.update(4, Author(0, "Akwaeke", "Emezi", "Akwaeke Emezi is a Nigerian fiction writer and video artist", "aemeziwritesbooks@email.com", "Book publisher", "emezi.com")))
            assertEquals("aemeziwritesbooks@email.com", populatedAuthors!!.findAuthor(4)!!.email)
            assertEquals("Book publisher", populatedAuthors!!.findAuthor(4)!!.publisher)
            assertEquals("emezi.com", populatedAuthors!!.findAuthor(4)!!.website)
        }
    }

//    @Nested
//    inner class DeleteAuthors {
//
//        @Test
//        fun `deleting a Note that does not exist, returns null`() {
//            assertNull(emptyAuthors!!.delete(0))
//            assertNull(populatedAuthors!!.delete(-1))
//            assertNull(populatedAuthors!!.delete(6))
//        }
//
//        @Test
//        fun `deleting a note that exists delete and returns deleted object`() {
//            assertEquals(6, populatedAuthors!!.numberOfAuthors())
//            assertEquals(akwaekeEmezi, populatedAuthors!!.delete(4))
//            assertEquals(5, populatedAuthors!!.numberOfAuthors())
//            assertEquals(kazuoIshiguro, populatedAuthors!!.delete(0))
//            assertEquals(4, populatedAuthors!!.numberOfAuthors())
//        }
//    }

    @Nested
    inner class ListNotes {

        @Test
        fun `listAllNotes returns No Notes Stored message when ArrayList is empty`() {
            assertEquals(0, emptyAuthors!!.numberOfAuthors())
            assertTrue(emptyAuthors!!.listAllAuthors().lowercase().contains("no notes"))
        }

        @Test
        fun `listAllNotes returns Notes when ArrayList has notes stored`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            val authorString = populatedAuthors!!.listAllAuthors().lowercase()
            assertTrue(authorString.contains("akwaeke"))
            assertTrue(authorString.contains("kazuo"))
            assertTrue(authorString.contains("brit"))
            assertTrue(authorString.contains("colson"))
            assertTrue(authorString.contains("sally"))
        }
    }
}