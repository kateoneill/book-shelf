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
        kazuoIshiguro = Author(0, "Kazuo", "Ishiguro", "Sir Kazuo Ishiguro OBE FRSA is a British novelist, screenwriter, musician, and short-story writer", "kishiguro@email.com", "Faber & Faber", "https://www.kazuoishiguro.com")
        SallyRooney = Author(0, "Sally", "Rooney", "Sally Rooney is an Irish author and screenwriter.", "srooney@email.com", "Penguin", "https://www.sally.com")
        TaylorJenkinsReid = Author(0, "Taylor","Jenkins Reid", "Taylor Jenkins Reid is an American author most known for her novel The Seven Husbands of Evelyn Hugo", "tjreidbooks@email.com", "Penguin", "https://www.taylorjenkinsreid.com")
        StephenKing = Author(0, "Stephen", "King", "Stephen King is an American author of horror, suspense, crime, science-fiction, and fantasy novels", "sking@email.com", "Simon & Schuster", "http://www.stephenking.com")
        akwaekeEmezi = Author(0, "Akwaeke","Emezi", "Akwaeke Emezi is a Nigerian fiction writer and video artist", "aemeziauthor@gmail.com", "Simon & Schuster", "https://www.sallyrooney.com")
        ColsonWhitehead = Author(0, "Colson", "Whitehead", "Arch Colson Chipp Whitehead is an American novelist. He is the author of eight novels.", "cwhitehead@email.com", "Little, Brown and Company", "http://www.colsonwhitehead.com")

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
            val newAuthor = Author(7, "Brit", "Bennett", "Brit Bennett is an American writer based in Los Angeles.", "bbennett@email.com", "Riverhead Books", "http://www.britbennett.com")
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            assertTrue(populatedAuthors!!.add(newAuthor))
            assertEquals(7, populatedAuthors!!.numberOfAuthors())
            assertEquals(newAuthor, populatedAuthors!!.findAuthor(populatedAuthors!!.numberOfAuthors() - 1))
        }

        @Test
        fun `adding an Author to an empty list adds to ArrayList`() {
            val newAuthor = Author(8, "Douglas", "Stuart", "Douglas Stuart is a Scottish-American writer and fashion designer", "dstuart@email.com", "Picador", "https://www.DouglasStuart.com")
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
            assertFalse(populatedAuthors!!.update(6, Author(0, "Maria", "Morgan", "Maria Morgan is from Wales", "mmorgan@email.com", "Simons", "http://www.mmorgan.co.uk")))
            assertFalse(populatedAuthors!!.update(-1, Author(0, "Tom", "Johns", "T.J is an established sci-fi writer", "tj.books@email.com", "Dead Books", "https://www.tjbooks.com")))
            assertFalse(emptyAuthors!!.update(0, Author(0, "Rua", "Lee", "Rua is a childrens fiction writer from Spain", "rlee@gmail.com", "Red herring ", "http://www.rua.com")))
        }

        @Test
        fun `updating a note that exists returns true and updates`() {
            // check note 5 exists and check the contents
            assertEquals(akwaekeEmezi, populatedAuthors!!.findAuthor(4))
            assertEquals("aemeziauthor@email.com", populatedAuthors!!.findAuthor(4)!!.email)
            assertEquals("Simon & Schuster", populatedAuthors!!.findAuthor(4)!!.publisher)
            assertEquals("https://www.sallyrooney.com", populatedAuthors!!.findAuthor(4)!!.website)

            // update note 5 with new information and ensure contents updated successfully
            assertTrue(populatedAuthors!!.update(4, Author(0, "Akwaeke", "Emezi", "Akwaeke Emezi is a Nigerian fiction writer and video artist", "aemeziwritesbooks@email.com", "Book publisher", "http://www.emezi.com")))
            assertEquals("aemeziwritesbooks@email.com", populatedAuthors!!.findAuthor(4)!!.email)
            assertEquals("Book publisher", populatedAuthors!!.findAuthor(4)!!.publisher)
            assertEquals("http://www.emezi.com", populatedAuthors!!.findAuthor(4)!!.website)
        }
    }

    @Nested
    inner class DeleteAuthors {

        @Test
        fun `deleting a Note that does not exist, returns null`() {
            assertFalse(emptyAuthors!!.delete(0))
            assertFalse(populatedAuthors!!.delete(-1))
            assertFalse(populatedAuthors!!.delete(6))
        }

        @Test
        fun `deleting a note that exists delete and returns deleted object`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            assertTrue(populatedAuthors!!.delete(4))
            assertEquals(5, populatedAuthors!!.numberOfAuthors())
            assertTrue(populatedAuthors!!.delete(0))
            assertEquals(4, populatedAuthors!!.numberOfAuthors())
        }
    }

    @Nested
    inner class ListAuthors{

        @Test
        fun `listAllAuthors returns No Authors Stored message when ArrayList is empty`() {
            assertEquals(0, emptyAuthors!!.numberOfAuthors())
            assertTrue(emptyAuthors!!.listAllAuthors().lowercase().contains("no notes"))
        }

        @Test
        fun `listAllAuthors returns Authors when ArrayList has notes stored`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            val authorString = populatedAuthors!!.listAllAuthors().lowercase()
            assertTrue(authorString.contains("akwaeke"))
            assertTrue(authorString.contains("kazuo"))
            assertTrue(authorString.contains("brit"))
            assertTrue(authorString.contains("colson"))
            assertTrue(authorString.contains("sally"))
        }

        @Test
        fun `listNotesByDueDate returns No Notes when ArrayList is empty`() {
            assertEquals(0, emptyAuthors!!.numberOfAuthors())
            assertTrue(emptyAuthors!!.listAuthorsByPublisher("brees books").lowercase().contains("no authors") )
        }

        @Test
        fun `listAuthorsByPublisher returns no authors when no authors with that publisher exist`() {
            // Priority 1 (1 note), 2 (none), 3 (1 note). 4 (2 notes), 5 (1 note)
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            val pubString = populatedAuthors!!.listAuthorsByPublisher("borgans").lowercase()
            assertTrue(pubString.contains("no authors"))
            assertTrue(pubString.contains("borgans"))
        }

        @Test
        fun `listNotesByDueDate returns all notes that match that due date when notes due then exist`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            val pubString = populatedAuthors!!.listAuthorsByPublisher("Simon & Schuster").lowercase()
            assertTrue(pubString.contains("akwaeke"))
            assertTrue(pubString.contains("stephen"))
            assertTrue(pubString.contains("sally"))
            assertFalse(pubString.contains("taylor"))
            assertFalse(pubString.contains("kazuo"))
            assertFalse(pubString.contains("colson"))

            val monthString = populatedAuthors!!.listAuthorsByPublisher("Penguin").lowercase()
            assertFalse(monthString.contains("akwaeke"))
            assertTrue(monthString.contains("sally"))
            assertTrue(monthString.contains("taylor"))
            assertFalse(monthString.contains("kazuo"))
            assertFalse(monthString.contains("colson"))
            assertFalse(monthString.contains("stephen"))
        }
    }

    @Nested
    inner class SearchMethods {
        //testing search by name
        @Test
        fun `search authors by first name returns when no authors with that name exist`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            val searchResults = populatedAuthors!!.searchByName("no results expected")
            assertTrue(searchResults.isEmpty())

            assertEquals(0, emptyAuthors!!.numberOfAuthors())
            assertTrue(emptyAuthors!!.searchByName("").isEmpty())
        }

        @Test
        fun `search authors by name returns authors when authors with that name exist`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())

            var searchResults = populatedAuthors!!.searchByName("Sally")
            assertTrue(searchResults.contains("Sally"))
            assertFalse(searchResults.contains("Stephen"))

            searchResults = populatedAuthors!!.searchByName("S")
            assertTrue(searchResults.contains("Sally"))
            assertTrue(searchResults.contains("Stephen"))
            assertFalse(searchResults.contains("Akwaeke"))

            searchResults = populatedAuthors!!.searchByName("TaY")
            assertTrue(searchResults.contains("Taylor"))
            assertFalse(searchResults.contains("Colson"))
        }

        //testing search by email
        @Test
        fun `search authors by emails returns when no authors with that email exist`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            val searchResults = populatedAuthors!!.searchByEmail("emailfiller@emailfiller.com")
            assertTrue(searchResults.isEmpty())

            assertEquals(0, emptyAuthors!!.numberOfAuthors())
            assertTrue(emptyAuthors!!.searchByEmail("").isEmpty())
        }

        @Test
        fun `search author by email returns authors when authors with that email exist`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())

            var searchResults = populatedAuthors!!.searchByEmail("srooney@email.com")
            assertTrue(searchResults.contains("Sally"))
            assertFalse(searchResults.contains("Stephen"))

            searchResults = populatedAuthors!!.searchByEmail("@email.com")
            assertTrue(searchResults.contains("Sally"))
            assertTrue(searchResults.contains("Stephen"))
            assertFalse(searchResults.contains("Akwaeke"))

            searchResults = populatedAuthors!!.searchByEmail("SkiNG@emAil.COm")
            assertTrue(searchResults.contains("Stephen"))
            assertFalse(searchResults.contains("Colson"))
        }
    }
}
