package com.rahul.notely.notedetail

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.rahul.notely.R
import com.rahul.notely.data.Note
import kotlinx.android.synthetic.main.activity_note_detail.*

class NoteDetailActivity : AppCompatActivity(),NoteDetailContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
        setSupportActionBar(toolbar)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_note_compose, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun showNoteList() {
    }

    override fun setNoteDetails(note: Note) {
    }

}
