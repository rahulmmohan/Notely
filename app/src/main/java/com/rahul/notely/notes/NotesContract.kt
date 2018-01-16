package com.rahul.notely.notes

import com.rahul.notely.data.Note

/**
 * Created by Rahul on 16/01/18.
 */
interface NotesContract {
    
    interface View {

        fun showNotes(Notes: List<Note>)

        fun openComposeNote()

        fun openNote(taskId: Int)

        fun showNoNotes()

        fun onNotesUpdate(message: String)

    }

    interface Presenter {

        fun loadNotes()

        fun addNewTask()

        fun openTaskDetails(task: Note)

        fun deleteNote(note: Note)

        fun makeNoteFavourite(note: Note, favourite: Boolean)

        fun makeNoteHearted(note: Note,hearted: Boolean)

    }
}