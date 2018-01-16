package com.rahul.notely.notes

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.rahul.notely.R
import com.rahul.notely.data.Note

import kotlinx.android.synthetic.main.activity_notes.*
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.rahul.notely.composenote.NoteComposeActivity
import kotlinx.android.synthetic.main.content_notes.*
import android.support.v7.widget.DividerItemDecoration


class NotesActivity : AppCompatActivity(), NotesContract.View,NotesAdapter.NoteItemListener{


    private lateinit var mPresenter: NotesContract.Presenter
    private var mNotesList = ArrayList<Note>()
    private var mAdapter: NotesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        setSupportActionBar(toolbar)

        mPresenter = NotesPresenter(this, this)
        setupRecyclerView()
        fab.setOnClickListener {
            mPresenter.addNewTask()
        }
    }

    override fun onResume() {
        super.onResume()
        mPresenter.loadNotes()
    }

   private fun setupRecyclerView(){
        mAdapter = NotesAdapter(this,mNotesList)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.itemAnimator =  DefaultItemAnimator()
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    override fun showNotes(Notes: List<Note>) {
        mNotesList.clear()
        mNotesList.addAll(Notes)
        mAdapter!!.notifyDataSetChanged()
        noNotes.visibility = View.GONE
    }

    override fun openComposeNote() {
        val intent = Intent(this, NoteComposeActivity::class.java)
        startActivity(intent)
    }

    override fun openNote(taskId: Int) {
    }

    override fun showNoNotes() {
        recyclerView.visibility = View.GONE
        noNotes.visibility = View.VISIBLE
    }

    override fun onNoteClick(note: Note) {
    }

    override fun onNoteDelete(note: Note) {
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
