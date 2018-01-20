package com.rahul.notely

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.rahul.notely.data.AppDatabase
import com.rahul.notely.data.Note
import com.rahul.notely.data.NoteDao
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Created by rahul on 16/1/18.
 */
@RunWith(AndroidJUnit4::class)
class NoteDaoTest{

    private var noteDao: NoteDao? = null

    @Before
    fun setup(){
        val context = InstrumentationRegistry.getTargetContext()
        noteDao = AppDatabase.getInstance(context).noteDao()
    }

    @Test
    fun should_Insert_Note_Item() {
        val note = Note()
        note.id = Random().nextInt()
        note.title = "title"
        note.details = "details"
        note.date = System.currentTimeMillis()

        noteDao?.insert(note)
        val noteTest = (noteDao?.getNote(note.id)!!)
        Assert.assertEquals(note.title,noteTest.title)
    }

    @Test
    fun should_Update_Note_Item() {
        val note = Note()
        note.id = Random().nextInt()
        note.title = "title"
        note.details = "detailsd"
        note.date = System.currentTimeMillis()

        noteDao?.insert(note)
        val noteCreated = (noteDao?.getNote(note.id)!!)
        noteCreated.details ="updated title"
        noteCreated.favourite = true
        noteDao?.update(noteCreated)
        val noteTest = (noteDao?.getNote(note.id)!!)
        Assert.assertEquals(noteCreated.details,noteTest.details)
    }
}