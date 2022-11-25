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
import kotlin.test.assertTrue

class AuthorAPITest {
    private var KazuoIshiguro: Author? = null
    private var SallyRooney: Author? = null
    private var TaylorJenkinsReid: Author? = null
    private var StephenKing: Author? = null
    private var AkwaekeEmezi: Author? = null
    private var ColsonWhitehead: Author? = null
    private var populatedAuthors: AuthorAPI? = AuthorAPI(XMLSerializer(File("notes.xml")))
    private var emptyAuthors: AuthorAPI? = AuthorAPI(XMLSerializer(File("notes.xml")))

    @BeforeEach
    fun setup() {
        KazuoIshiguro = Author(1, "Kazuo", "Ishiguro", "Sir Kazuo Ishiguro OBE FRSA FRSL is a British novelist, screenwriter, musician, and short-story writer", "kishiguro@email.com", "Faber & Faber", "kazuoishiguro.com")
        SallyRooney = Author(2, "Sally", "Rooney", "Sally Rooney is an Irish author and screenwriter.", "srooney@email.com", "Penguin", "sally")
        TaylorJenkinsReid = Author(3, "Taylor","Jenkins Reid", "Taylor Jenkins Reid is an American author most known for her novels The Seven Husbands of Evelyn Hugo, Daisy Jones & The Six, and Malibu Rising.", "tjreidbooks@email.com", "Penguin", "taylorjenkinsreid.com")
        StephenKing = Author(4, "Stephen", "King", "Stephen Edwin King is an American author of horror, supernatural fiction, suspense, crime, science-fiction, and fantasy novels", "sking@email.com", "Simon & Schuster", "stephenking.com")
        AkwaekeEmezi = Author(5, "Akwaeke","Emezi", "Akwaeke Emezi is a Nigerian fiction writer and video artist", "aemeziauthor@email.com", "Simon & Schuster", "sallyrooney.com")
        ColsonWhitehead = Author(6, "Colson", "Whitehead", "Arch Colson Chipp Whitehead is an American novelist. He is the author of eight novels.", "cwhitehead@email.com", "Little, Brown and Company", "colsonwhitehead.com")

        // adding 5 Note to the notes api
        populatedAuthors!!.add(KazuoIshiguro!!)
        populatedAuthors!!.add(SallyRooney!!)
        populatedAuthors!!.add(TaylorJenkinsReid!!)
        populatedAuthors!!.add(StephenKing!!)
        populatedAuthors!!.add(AkwaekeEmezi!!)
        populatedAuthors!!.add(ColsonWhitehead!!)
    }

    @AfterEach
    fun tearDown() {
        KazuoIshiguro = null
        SallyRooney = null
        TaylorJenkinsReid = null
        StephenKing = null
        AkwaekeEmezi = null
        ColsonWhitehead = null
        emptyAuthors = null
    }

    @Nested
    inner class AddAuthors {
        @Test
        fun `adding a Note to a populated list adds to ArrayList`() {
            val newAuthor = Author(7, "Brit", "Bennett", "Brit Bennett is an American writer based in Los Angeles.", "bbennett@email.com", "Riverhead Books", "britbennett.com")
            assertEquals(6, populatedAuthors!!.numberOfAuthors())
            assertTrue(populatedAuthors!!.add(newAuthor))
            assertEquals(7, populatedAuthors!!.numberOfAuthors())
            assertEquals(newAuthor, populatedAuthors!!.findAuthor(populatedAuthors!!.numberOfAuthors() - 1))
        }

        @Test
        fun `adding a Note to an empty list adds to ArrayList`() {
            val newAuthor = Author(8, "Douglas", "Stuart", "Douglas Stuart is a Scottish-American writer and fashion designer", "dstuart@email.com", "Picador", "DouglasStuart.com")
            assertEquals(0, emptyAuthors!!.numberOfAuthors())
            assertTrue(emptyAuthors!!.add(newAuthor))
            assertEquals(1, emptyAuthors!!.numberOfAuthors())
            assertEquals(newAuthor, emptyAuthors!!.findAuthor(emptyAuthors!!.numberOfAuthors() - 1))
        }
    }

}