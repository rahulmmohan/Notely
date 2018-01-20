package com.rahul.notely.composenote

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import com.rahul.notely.R
import com.rahul.notely.data.Note
import kotlinx.android.synthetic.main.activity_note_compose.*

class NoteComposeActivity : AppCompatActivity(), NoteComposeContract.View {

    private lateinit var mPresenter: NoteComposeContract.Presenter
    private var isUndoVisible = false
    private var isDataSet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_compose)
        setSupportActionBar(toolbar)
        val noteId = intent.getIntExtra(getString(R.string.note_id), -1)
        mPresenter = NoteComposePresenter(this, noteId, this)

        noteTitle.addTextChangedListener(textChangeListener)
        noteDescription.addTextChangedListener(textChangeListener)
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
        isDataSet = true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_note_compose, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private var register: MenuItem? = null

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        register = menu.findItem(R.id.action_undo)
        register!!.isVisible = isUndoVisible
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.action_save -> saveNote()
            R.id.action_undo -> {
                mPresenter.fetchNote()
                isUndoVisible = false
                invalidateOptionsMenu()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveNote() {
        val titleText = noteTitle.text.toString()
        val descText = noteDescription.text.toString()
        if (titleText.isEmpty() || descText.isEmpty()) {
            Snackbar.make(coordinator, R.string.error_empty, Snackbar.LENGTH_LONG).show()
        } else {
            mPresenter.saveNote(titleText, descText)
        }
    }

    private val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (isDataSet && register != null && !register!!.isVisible) {
                isUndoVisible = true
                invalidateOptionsMenu()
            }
        }
    }
}
