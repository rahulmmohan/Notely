package com.rahul.notely.notedetail

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.rahul.notely.R
import com.rahul.notely.composenote.NoteComposeActivity
import com.rahul.notely.data.Note
import kotlinx.android.synthetic.main.activity_note_detail.*
import kotlinx.android.synthetic.main.content_note_detail.*

class NoteDetailActivity : AppCompatActivity(),NoteDetailContract.View {

    private lateinit var mPresenter:NoteDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
        setSupportActionBar(toolbar)

        val taskId = intent.getIntExtra(getString(R.string.note_id), -1)
        mPresenter = NoteDetailPresenter(this, taskId, this)

    }

    override fun onResume() {
        super.onResume()
        mPresenter.fetchNote()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_note_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_edit -> {
                mPresenter.editNote()
                return true
            }
            android.R.id.home -> finish()
        }
        return false
    }
    override fun showNoteList() {
    }

    override fun setNoteDetails(note: Note) {
        noteTitle.text = note.title
        noteDate.text = note.msToDate()
        noteDescription.text = note.details
    }

    override fun showEditNote(noteId: Int) {
        val intent = Intent(this, NoteComposeActivity::class.java)
        intent.putExtra(getString(R.string.note_id), noteId)
        startActivity(intent)
    }


}
