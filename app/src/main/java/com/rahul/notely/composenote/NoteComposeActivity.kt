package com.rahul.notely.composenote

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.rahul.notely.R
import com.rahul.notely.data.Note
import kotlinx.android.synthetic.main.activity_note_compose.*

class NoteComposeActivity : AppCompatActivity(), NoteComposeContract.View {

    private lateinit var mPresenter:NoteComposeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_compose)
        setSupportActionBar(toolbar)

        val taskId = intent.getIntExtra(getString(R.string.note_id), -1)
        mPresenter = NoteComposePresenter(this, taskId, this)

    }

    override fun onResume() {
        super.onResume()
        mPresenter.fetchNote()
    }

    override fun showNoteList() {
        finish()
    }

    override fun setNoteDetails(note: Note) {
        noteTitle.setText(note.title)
        noteDescription.setText(note.details)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_note_compose, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.action_save -> saveNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveNote(){
        val titleText = noteTitle.text.toString()
        val descText = noteDescription.text.toString()
        if (titleText.isEmpty() || descText.isEmpty()) {
            Snackbar.make(coordinator, R.string.error_empty, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        } else {
            mPresenter.saveNote(titleText, descText)
        }
    }
}
