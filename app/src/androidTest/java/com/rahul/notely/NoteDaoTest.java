package com.rahul.notely;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.rahul.notely.data.AppDatabase;
import com.rahul.notely.data.Note;
import com.rahul.notely.data.NoteDao;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Rahul on 21/01/18.
 */

@RunWith(AndroidJUnit4.class)
public class NoteDaoTest {
    private NoteDao noteDao = null;

    @Before
    public void setup() {
        // Context of the app under filter_text_color_toggle.
        Context appContext = InstrumentationRegistry.getTargetContext();
        noteDao = AppDatabase.Companion.getInstance(appContext).noteDao();
    }

    @Test
    public void should_Insert_Note_Item() {
        Note note = new Note();
        note.setId(new Random().nextInt());
        note.setTitle("title");
        note.setDetails("details");

        noteDao.insert(note);
        Note noteTest = (noteDao.getNote(note.getId()));
        Assert.assertEquals(note.getTitle(),noteTest.getTitle());
    }

    @Test
    public void should_Update_Note_Item() {
        Note note = new Note();
        note.setId(new Random().nextInt());
        note.setTitle("title");
        note.setDetails("details");

        noteDao.insert(note);
        Note noteCreated = (noteDao.getNote(note.getId()));
        noteCreated.setDetails("updated title");
        noteDao.update(noteCreated);
        Note noteTest = (noteDao.getNote(note.getId()));
        Assert.assertEquals(noteCreated.getDetails(),noteTest.getDetails());
    }

    @Test
    public void should_Delete_Note_Item() {
        Note note = new Note();
        note.setId(new Random().nextInt());
        note.setTitle("title");
        note.setDetails("details");

        noteDao.insert(note);
        noteDao.delete(note);
        Assert.assertNull(noteDao.getNote(note.getId()));
    }

}
