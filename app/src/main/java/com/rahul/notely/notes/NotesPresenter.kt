package com.rahul.notely.notes

import android.content.Context
import com.rahul.notely.data.AppDatabase
import com.rahul.notely.data.Note

/**
 * Listens to user actions from the UI ([NotesActivity]), retrieves the data and updates the
 * UI as required.
 */
class NotesPresenter(context: Context,
                     private val mTasksView: NotesContract.View) : NotesContract.Presenter {

    private val db = AppDatabase.getInstance(context)

    override fun loadNotes() {
        processTasks(db.noteDao().getAll())
    }


    private fun processTasks(tasks: List<Note>) {
        if (tasks.isEmpty()) {
            // Show a message indicating there are no tasks for that filter type.
            mTasksView.showNoNotes()
        } else {
            // Show the list of tasks
            mTasksView.showNotes(tasks)
        }
    }


    override fun addNewTask() {
        mTasksView.openComposeNote()
    }

    override fun openTaskDetails(requestedTask: Note) {
        mTasksView.openNote(requestedTask.id)
    }

    override fun deleteNote(note: Note) {
    }

    override fun makeNoteFavourite(note: Note, favourite: Boolean) {
        note.favourite = favourite
        db.noteDao().update(note)
        mTasksView.onNotesUpdate("Note made favourite")
    }

    override fun makeNoteHearted(note: Note, hearted: Boolean) {
        note.hearted = hearted
        db.noteDao().update(note)
        mTasksView.onNotesUpdate("Note Hearted")
    }

}
