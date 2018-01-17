package com.rahul.notely.notes

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.rahul.notely.R
import com.rahul.notely.data.Note

import kotlinx.android.synthetic.main.notes_layout.*
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.rahul.notely.composenote.NoteComposeActivity
import kotlinx.android.synthetic.main.content_notes.*
import android.support.v7.widget.DividerItemDecoration
import android.view.Menu
import android.view.MenuItem
import com.rahul.notely.notedetail.NoteDetailActivity
import kotlinx.android.synthetic.main.activity_notes.*


class NotesActivity : AppCompatActivity(), NotesContract.View,NotesAdapter.NoteItemListener{


    private lateinit var mPresenter: NotesContract.Presenter
    private lateinit var mAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        setSupportActionBar(toolbar)

        mPresenter = NotesPresenter(this, this)
        setupRecyclerView()
        addNote.setOnClickListener {
            mPresenter.addNewNote()
        }
    }

    override fun onResume() {
        super.onResume()
        mPresenter.loadNotes()
    }

   private fun setupRecyclerView(){
        mAdapter = NotesAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.itemAnimator =  DefaultItemAnimator()
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_notes, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter -> {
                mainLaout.toggleMenu()
                return true
            }
        }
        return false
    }

    override fun showNotes(notes: List<Note>) {
        mAdapter.replaceData(notes)
        recyclerView.visibility = View.VISIBLE
        noNotes.visibility = View.GONE
    }

    override fun openComposeNote() {
        val intent = Intent(this, NoteComposeActivity::class.java)
        startActivity(intent)
    }

    override fun openNote(noteId: Int) {
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(getString(R.string.note_id), noteId)
        startActivity(intent)
    }

    override fun showNoNotes() {
        recyclerView.visibility = View.GONE
        noNotes.visibility = View.VISIBLE
    }

    override fun onNoteClick(note: Note) {
        mPresenter.openNoteDetails(note)
    }

    override fun onNoteDelete(note: Note,position: Int) {
        mPresenter.deleteNote(note)
        mAdapter.removeAt(position)
    }

    override fun makeNoteHearted(note: Note, hearted: Boolean) {
        mPresenter.makeNoteHearted(note,hearted)
    }

    override fun makeNoteFavourite(note: Note, favourite: Boolean) {
        mPresenter.makeNoteFavourite(note,favourite)
    }

    override fun onNotesUpdate(message:String) {
        Snackbar.make(coordinator, message, Snackbar.LENGTH_LONG).show()
    }

}
