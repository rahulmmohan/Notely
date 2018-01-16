package com.rahul.notely

import android.app.Instrumentation
import android.content.pm.InstrumentationInfo
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.rahul.notely.data.AppDatabase
import com.rahul.notely.data.Note
import com.rahul.notely.data.NoteDao
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

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
        note.id = 1
        note.title = "title"
        note.details = "details"
        note.date = System.currentTimeMillis()

        noteDao?.insert(note)
        val noteTest = (noteDao?.getNote(note.id)!!)
        Assert.assertEquals(note.title,noteTest.title)
    }
}