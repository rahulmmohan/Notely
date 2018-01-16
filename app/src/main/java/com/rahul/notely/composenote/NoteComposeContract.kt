package com.rahul.notely.composenote


/**
 * Created by rahul on 25/8/17.
 */

import com.rahul.notely.data.Note

/**
 * This specifies the contract between the view and the presenter.
 */
interface NoteComposeContract {

    interface View {

        fun showNoteList()

        fun setNoteDetails(note: Note)
    }

    interface Presenter {

        fun saveNote(title: String, description: String)

        fun fetchNote()

    }
}
