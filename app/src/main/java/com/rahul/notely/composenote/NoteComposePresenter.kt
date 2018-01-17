package com.rahul.notely.composenote


/**
 * Created by rahul on 25/8/17.
 */

import android.content.Context
import com.rahul.notely.data.AppDatabase
import com.rahul.notely.data.Note

/**
 * Listens to user actions from the UI ([]), retrieves the data and updates
 * the UI as required.
 */
/**
 * Creates a presenter for the add/edit view.
 *
 * @param taskId      ID of the task to edit or null for a new task
 * @param addTaskView the add/edit view
 */
class NoteComposePresenter( context: Context,
                           private val noteId: Int,
                           private val noteComposeView: NoteComposeContract.View) : NoteComposeContract.Presenter {

    private val db = AppDatabase.getInstance(context)

    private val isNewTask: Boolean
        get() = noteId == -1

    override fun saveNote(title: String, description: String) {
        if (isNewTask) {
            createNote(title, description);
        } else {
            updateNote(title, description);
        }
        noteComposeView.showNoteList()
    }

    override fun fetchNote() {
        if (isNewTask) return
        var note = db.noteDao().getNote(noteId)
        noteComposeView.setNoteDetails(note)
    }

    private fun createNote(title: String, description: String) {
        var note = Note()
        note.title = title
        note.details = description
        note.date = System.currentTimeMillis()
        db.noteDao().insert(note)
    }
    private fun updateNote(title: String, description: String) {
        var note = db.noteDao().getNote(noteId)
        note.title = title
        note.details = description
        note.date = System.currentTimeMillis()
        db.noteDao().update(note)
    }

}
