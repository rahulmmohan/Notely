package com.rahul.notely.notedetail

import com.rahul.notely.data.Note

/**
 * Created by Rahul on 16/01/18.
 */
class NoteDetailContract {

    interface View {

        fun showNoteList()

        fun setNoteDetails(note: Note)

         fun showEditNote(noteId: Int)
    }

    interface Presenter {

         fun editNote()

         fun fetchNote()

    }
}