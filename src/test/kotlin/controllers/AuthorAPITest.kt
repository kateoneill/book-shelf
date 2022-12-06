package controllers

import models.Author
import models.Book
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.JSONSerializer
import persistence.XMLSerializer
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AuthorAPITest {
    private var kazuoIshiguro: Author? = null
    private var SallyRooney: Author? = null
    private var TaylorJenkinsReid: Author? = null
    private var StephenKing: Author? = null
    private var akwaekeEmezi: Author? = null
    private var ColsonWhitehead: Author? = null
    private var NeverGo : Book? = null
    private var ArtistWorld: Book? = null
    private var BuriedGiant: Book? = null

    private var populatedAuthors: AuthorAPI? = AuthorAPI(XMLSerializer(File("notes.xml")))
    private var emptyAuthors: AuthorAPI? = AuthorAPI(XMLSerializer(File("notes.xml")))

    @BeforeEach
    fun setup() {
        kazuoIshiguro = Author(0, "Kazuo", "Ishiguro", "Sir Kazuo Ishiguro OBE FRSA is a British novelist, screenwriter, musician, and short-story writer", "kishiguro@email.com", "Faber & Faber", "https://www.kazuoishiguro.com",mutableSetOf<Book>())
        SallyRooney = Author(0, "Sally", "Rooney", "Sally Rooney is an Irish author and screenwriter.", "srooney@email.com", "Penguin", "https://www.sally.com")
        TaylorJenkinsReid = Author(0, "Taylor","Jenkins Reid", "Taylor Jenkins Reid is an American author most known for her novel The Seven Husbands of Evelyn Hugo", "tjreidbooks@email.com", "Penguin", "https://www.taylorjenkinsreid.com")
        StephenKing = Author(0, "Stephen", "King", "Stephen King is an American author of horror, suspense, crime, science-fiction, and fantasy novels", "sking@email.com", "Simon & Schuster", "http://www.stephenking.com")
        akwaekeEmezi = Author(0, "Akwaeke","Emezi", "Akwaeke Emezi is a Nigerian fiction writer and video artist", "aemeziauthor@gmail.com", "Simon & Schuster", "https://www.akwa.com")
        ColsonWhitehead = Author(0, "Colson", "Whitehead", "Arch Colson Chipp Whitehead is an American novelist. He is the author of eight novels.", "cwhitehead@email.com", "Little, Brown and Company", "http://www.colsonwhitehead.com")
        NeverGo = Book(0, "Never let me go", 5, "Literary fiction", "medium", true, 120, "currently reading")
        ArtistWorld = Book(0, "An artist of the floating world",4, "Mystery", "fast", false, 200,"finished")
        BuriedGiant = Book(0, "The buried giant", 5, "Thriller", "slow", false, 250,"currently reading")

        // adding 5 Note to the notes api
        populatedAuthors!!.add(kazuoIshiguro!!)
        populatedAuthors!!.add(SallyRooney!!)
        populatedAuthors!!.add(TaylorJenkinsReid!!)
        populatedAuthors!!.add(StephenKing!!)
        populatedAuthors!!.add(akwaekeEmezi!!)
        populatedAuthors!!.add(ColsonWhitehead!!)
        kazuoIshiguro!!.addBook(NeverGo!!)
        kazuoIshiguro!!.addBook(ArtistWorld!!)
        kazuoIshiguro!!.addBook(BuriedGiant!!)
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
        NeverGo = null
        ArtistWorld = null
        BuriedGiant = null
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
        fun `listAuthorsByPublisher returns No authors when ArrayList is empty`() {
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
        fun `listNotesByPublisher returns all authors that match that publisher when authors with that publisher exist`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            val pubString = populatedAuthors!!.listAuthorsByPublisher("Simon & Schuster").lowercase()
            assertTrue(pubString.contains("akwaeke"))
            assertTrue(pubString.contains("stephen"))
            assertFalse(pubString.contains("sally"))
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

        @Test
        fun `listAuthorsBySurname returns No authors when ArrayList is empty`() {
            assertEquals(0, emptyAuthors!!.numberOfAuthors())
            assertTrue(emptyAuthors!!.listAuthorsBySurname("quinn").lowercase().contains("no authors") )
        }

        @Test
        fun `listAuthorsBySurname returns no authors when no authors with that surname exist`() {
            // Priority 1 (1 note), 2 (none), 3 (1 note). 4 (2 notes), 5 (1 note)
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            val pubString = populatedAuthors!!.listAuthorsBySurname("quinn").lowercase()
            assertTrue(pubString.contains("no authors"))
            assertTrue(pubString.contains("quinn"))
        }

        @Test
        fun `listAuthorsBySurname returns all authors that match that surname when authors due then exist`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            val pubString = populatedAuthors!!.listAuthorsBySurname("Emezi").lowercase()
            assertTrue(pubString.contains("akwaeke"))
            assertFalse(pubString.contains("stephen"))
            assertFalse(pubString.contains("sally"))
            assertFalse(pubString.contains("taylor"))
            assertFalse(pubString.contains("kazuo"))
            assertFalse(pubString.contains("colson"))

            val monthString = populatedAuthors!!.listAuthorsBySurname("King").lowercase()
            assertFalse(monthString.contains("akwaeke"))
            assertFalse(monthString.contains("sally"))
            assertFalse(monthString.contains("taylor"))
            assertFalse(monthString.contains("kazuo"))
            assertFalse(monthString.contains("colson"))
            assertTrue(monthString.contains("stephen"))
        }

        @Test
        fun `listBooksMarkedOwned returns No books when ArrayList is empty`() {
            assertEquals(0, emptyAuthors!!.numberOfAuthors())
            assertTrue(emptyAuthors!!.listBooksMarkedOwned().lowercase().contains("no authors") )
        }

        @Test
        fun `listBooksMarkedOwned returns no authors when no authors with books marked owned exist`() {
            // Priority 1 (1 note), 2 (none), 3 (1 note). 4 (2 notes), 5 (1 note)
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            val pubString = populatedAuthors!!.listBooksMarkedOwned().lowercase()
            assertFalse(pubString.contains("sally"))
            assertFalse(pubString.contains("colson"))
            assertFalse(pubString.contains("taylor"))
        }

        @Test
        fun `listBooksMarkedOwned returns all authors with books that are marked owned`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            val pubString = populatedAuthors!!.listBooksMarkedOwned().lowercase()
            assertTrue(pubString.contains("kazuo"))
            assertFalse(pubString.contains("stephen"))
            assertFalse(pubString.contains("colson"))
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

        @Test
        fun `search books by title returns no books when no books with that title exist`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            assertEquals(3, kazuoIshiguro!!.numberOfBooks())
            val testTitle = populatedAuthors!!.searchBookByTitle("Should be EMPty").lowercase()
            assertTrue(testTitle.contains("no books"))

            assertEquals(0, emptyAuthors!!.numberOfAuthors())
            val testTitle2 = emptyAuthors!!.searchBookByTitle("Should be EMPty").lowercase()
            assertFalse(testTitle2.contains("Should be EMPty"))
        }

        @Test
        fun `search books by title returns books when books with that title exist`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())

            var searchResults = populatedAuthors!!.searchBookByTitle("Never let me go").lowercase()
            assertTrue(searchResults.contains("kazuo"))
            assertFalse(searchResults.contains("sally"))

            searchResults = populatedAuthors!!.searchBookByTitle("the")
            assertTrue(searchResults.contains("giant"))
            assertTrue(searchResults.contains("artist"))
            assertFalse(searchResults.contains("never"))

            searchResults = populatedAuthors!!.searchBookByTitle("NeVeR LET mE gO").lowercase()
            assertTrue(searchResults.contains("never let me go"))
            assertFalse(searchResults.contains("the buried giant"))
        }

        @Test
        fun `search books by genre returns no books when no books with that genre exist`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            assertEquals(3, kazuoIshiguro!!.numberOfBooks())
            val testTitle = populatedAuthors!!.searchBookByGenre("Memoir").lowercase()
            assertTrue(testTitle.contains("no books"))

            assertEquals(0, emptyAuthors!!.numberOfAuthors())
            val testTitle2 = emptyAuthors!!.searchBookByGenre("Memoir").lowercase()
            assertFalse(testTitle2.contains("Memoir"))
        }

        @Test
        fun `search books by genre returns books when books with that genre exist`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())

            var searchResults = populatedAuthors!!.searchBookByGenre("Mystery").lowercase()
            assertTrue(searchResults.contains("kazuo"))
            assertFalse(searchResults.contains("sally"))

            searchResults = populatedAuthors!!.searchBookByGenre("Thriller")
            assertTrue(searchResults.contains("giant"))
            assertTrue(searchResults.contains("buried"))
            assertFalse(searchResults.contains("never"))

            searchResults = populatedAuthors!!.searchBookByGenre("MySTerY").lowercase()
            assertTrue(searchResults.contains("an artist of the floating world"))
            assertFalse(searchResults.contains("the buried giant"))
        }

        @Test
        fun `search books by length returns no books when no books with that length exist`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            assertEquals(3, kazuoIshiguro!!.numberOfBooks())
            val testTitle = populatedAuthors!!.searchBookByLength(900).lowercase()
            assertTrue(testTitle.contains("no books"))

            assertEquals(0, emptyAuthors!!.numberOfAuthors())
            val testTitle2 = emptyAuthors!!.searchBookByLength(900).lowercase()
            assertFalse(testTitle2.equals(900))
        }

        @Test
        fun `search books by length returns books when books with that length exist`() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())

            var searchResults = populatedAuthors!!.searchBookByLength(120).lowercase()
            assertTrue(searchResults.contains("kazuo"))
            assertFalse(searchResults.contains("stephen"))

            searchResults = populatedAuthors!!.searchBookByLength(200)
            assertTrue(searchResults.contains("artist"))
            assertTrue(searchResults.contains("world"))
            assertFalse(searchResults.contains("never"))

            searchResults = populatedAuthors!!.searchBookByLength(250).lowercase()
            assertTrue(searchResults.contains("the buried giant"))
            assertFalse(searchResults.contains("the artist of the floating world"))
        }
    }

    @Nested
    inner class CountingMethods {

        @Test
        fun numberOfNotesCalculatedCorrectly() {
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            assertEquals(0, emptyAuthors!!.numberOfAuthors())
        }

        @Test
        fun numberOfNotesByPublisherCalculatedCorrectly() {
            assertEquals(2, populatedAuthors!!.numberOfAuthorsByPublisher("Simon & Schuster"))
            assertEquals(2, populatedAuthors!!.numberOfAuthorsByPublisher("Penguin"))
            assertEquals(1, populatedAuthors!!.numberOfAuthorsByPublisher("Faber & Faber"))
            assertEquals(1, populatedAuthors!!.numberOfAuthorsByPublisher("Little, Brown and Company"))
            assertEquals(0, emptyAuthors!!.numberOfAuthors())
        }

        @Test
        fun numberOfNotesBySurnameCalculatedCorrectly() {
            assertEquals(1, populatedAuthors!!.numberOfAuthorsBySurname("Ishiguro"))
            assertEquals(1, populatedAuthors!!.numberOfAuthorsBySurname("Emezi"))
            assertEquals(1, populatedAuthors!!.numberOfAuthorsBySurname("Rooney"))
            assertEquals(1, populatedAuthors!!.numberOfAuthorsBySurname("Whitehead"))
            assertEquals(1, populatedAuthors!!.numberOfAuthorsBySurname("Jenkins Reid"))
            assertEquals(1, populatedAuthors!!.numberOfAuthorsBySurname("King"))
            assertEquals(0, emptyAuthors!!.numberOfAuthors())
        }

        @Test
        fun numberOfBooksMarkedOwnedCalculatedCorrectly() {
            assertEquals(1, populatedAuthors!!.numberOfBooksMarkedOwned())
            assertEquals(0, emptyAuthors!!.numberOfAuthors())
        }
    }

    @Nested
    inner class PersistenceTests {

        @Test
        fun `saving and loading an empty collection in XML doesn't crash app`() {
            // Saving an empty notes.XML file.
            val storingNotes = AuthorAPI(XMLSerializer(File("notes.xml")))
            storingNotes.store()

            // Loading the empty notes.xml file into a new object
            val loadedNotes = AuthorAPI(XMLSerializer(File("notes.xml")))
            loadedNotes.load()

            // Comparing the source of the notes (storingNotes) with the XML loaded notes (loadedNotes)
            assertEquals(0, storingNotes.numberOfAuthors())
            assertEquals(0, loadedNotes.numberOfAuthors())
            assertEquals(storingNotes.numberOfAuthors(), loadedNotes.numberOfAuthors())
        }

        @Test
        fun `saving and loading an loaded collection in XML doesn't loose data`() {
            // Storing 3 notes to the notes.XML file.
            val storingNotes = AuthorAPI(XMLSerializer(File("notes.xml")))
            storingNotes.add(kazuoIshiguro!!)
            storingNotes.add(SallyRooney!!)
            storingNotes.add(ColsonWhitehead!!)
            storingNotes.store()

            // Loading notes.xml into a different collection
            val loadedNotes = AuthorAPI(XMLSerializer(File("notes.xml")))
            loadedNotes.load()

            // Comparing the source of the notes (storingNotes) with the XML loaded notes (loadedNotes)
            assertEquals(3, storingNotes.numberOfAuthors())
            assertEquals(3, loadedNotes.numberOfAuthors())
            assertEquals(storingNotes.numberOfAuthors(), loadedNotes.numberOfAuthors())
            assertEquals(storingNotes.findAuthor(0), loadedNotes.findAuthor(0))
            assertEquals(storingNotes.findAuthor(1), loadedNotes.findAuthor(1))
            assertEquals(storingNotes.findAuthor(2), loadedNotes.findAuthor(2))
        }

        @Test
        fun `saving and loading an empty collection in JSON doesn't crash app`() {
            // Saving an empty notes.json file.
            val storingNotes = AuthorAPI(JSONSerializer(File("notes.json")))
            storingNotes.store()

            // Loading the empty notes.json file into a new object
            val loadedNotes = AuthorAPI(JSONSerializer(File("notes.json")))
            loadedNotes.load()

            // Comparing the source of the notes (storingNotes) with the json loaded notes (loadedNotes)
            assertEquals(0, storingNotes.numberOfAuthors())
            assertEquals(0, loadedNotes.numberOfAuthors())
            assertEquals(storingNotes.numberOfAuthors(), loadedNotes.numberOfAuthors())
        }

        @Test
        fun `saving and loading an loaded collection in JSON doesn't loose data`() {
            // Storing 3 notes to the notes.json file.
            val storingNotes = AuthorAPI(JSONSerializer(File("notes.json")))
            storingNotes.add(kazuoIshiguro!!)
            storingNotes.add(SallyRooney!!)
            storingNotes.add(ColsonWhitehead!!)
            storingNotes.store()

            // Loading notes.json into a different collection
            val loadedNotes = AuthorAPI(JSONSerializer(File("notes.json")))
            loadedNotes.load()

            // Comparing the source of the notes (storingNotes) with the json loaded notes (loadedNotes)
            assertEquals(3, storingNotes.numberOfAuthors())
            assertEquals(3, loadedNotes.numberOfAuthors())
            assertEquals(storingNotes.numberOfAuthors(), loadedNotes.numberOfAuthors())
            assertEquals(storingNotes.findAuthor(0), loadedNotes.findAuthor(0))
            assertEquals(storingNotes.findAuthor(1), loadedNotes.findAuthor(1))
            assertEquals(storingNotes.findAuthor(2), loadedNotes.findAuthor(2))
        }
    }
}
