package com.rahul.notely.notes

import android.content.Context
import com.rahul.notely.data.AppDatabase
import com.rahul.notely.data.Note

/**
 * Listens to user actions from the UI ([NotesActivity]), retrieves the data and updates the
 * UI as required.
 */
class NotesPresenter(context: Context,
                     private val mNotesView: NotesContract.View) : NotesContract.Presenter {

    private val db = AppDatabase.getInstance(context)

    override fun loadNotes(filter: Filter) {
        processTasks(db.noteDao().getAll())
    }


    private fun processTasks(notes: List<Note>) {
        if (notes.isEmpty()) {
            // Show a message indicating there are no notes for that filter type.
            mNotesView.showNoNotes()
        } else {
            // Show the list of notes
            mNotesView.showNotes(notes)
        }
    }


    override fun addNewNote() {
        mNotesView.openComposeNote()
    }

    override fun openNoteDetails(requestedNote: Note) {
        mNotesView.openNote(requestedNote.id)
    }

    override fun deleteNote(note: Note) {
        db.noteDao().delete(note)
    }

    override fun makeNoteFavourite(note: Note, favourite: Boolean) {
        note.favourite = favourite
        db.noteDao().update(note)
    }

    override fun makeNoteHearted(note: Note, hearted: Boolean) {
        note.hearted = hearted
        db.noteDao().update(note)
    }

}
