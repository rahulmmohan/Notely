package com.rahul.notely.notes

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import com.rahul.notely.R
import com.rahul.notely.composenote.NoteComposeActivity
import com.rahul.notely.data.Note
import com.rahul.notely.notedetail.NoteDetailActivity
import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.android.synthetic.main.content_notes.*
import kotlinx.android.synthetic.main.filter_layout.*
import kotlinx.android.synthetic.main.notes_layout.*


class NotesActivity : AppCompatActivity(), NotesContract.View, NotesAdapter.NoteItemListener {


    private lateinit var mPresenter: NotesContract.Presenter
    private lateinit var mAdapter: NotesAdapter
    private var filter = Filter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        setSupportActionBar(toolbar)

        mPresenter = NotesPresenter(this, this)
        setupRecyclerView()
        addNote.setOnClickListener {
            mPresenter.addNewNote()
        }
        filterSettings()
    }

    private fun filterSettings() {
        applyFilter.setOnClickListener {
            filter.markFilter(Filter.FilterType.HEARTED, filterHearted.isChecked)
            filter.markFilter(Filter.FilterType.FAVOURITE, filterFavourite.isChecked)
            filterBubble.visibility = if (filter.appliedFilters.isEmpty()) View.GONE else View.VISIBLE
            mPresenter.loadNotes(filter)
            filterMenuItem.visibility = View.VISIBLE
            filterLayout.toggleRightSlide()
        }
        filterClose.setOnClickListener { filterLayout.toggleRightSlide(); }
        filterLayout.addOnSlideChangedListener { _, _, isRightSlideOpen ->
            if (isRightSlideOpen) {
                filterMenuItem.visibility = View.GONE
            } else {
                filterMenuItem.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mPresenter.loadNotes(filter)
    }

    private fun setupRecyclerView() {
        mAdapter = NotesAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager.layoutDirection
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_notes, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private lateinit var filterBubble: View

    private lateinit var filterMenuItem: FrameLayout

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val menuItem = menu.findItem(R.id.action_filter)
        filterMenuItem = menuItem.actionView as FrameLayout
        filterBubble = filterMenuItem.findViewById(R.id.filter_bubble)
        filterMenuItem.setOnClickListener { onOptionsItemSelected(menuItem) }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter -> {
                filterHearted.isChecked = filter.appliedFilters.contains(Filter.FilterType.HEARTED)
                filterFavourite.isChecked = filter.appliedFilters.contains(Filter.FilterType.FAVOURITE)
                filterLayout.toggleRightSlide()
                return true
            }
        }
        return false
    }

    override fun showNotes(notes: List<Note>) {
        mAdapter.replaceData(notes)
        recyclerView.visibility = View.VISIBLE
        noNotes.visibility = View.INVISIBLE
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
        recyclerView.visibility = View.INVISIBLE
        noNotes.visibility = View.VISIBLE
    }

    override fun onNoteClick(note: Note) {
        mPresenter.openNoteDetails(note)
    }

    override fun onNoteDelete(note: Note, position: Int) {
        mPresenter.deleteNote(note)
        mAdapter.removeAt(position)
        if (mAdapter.itemCount == 0) showNoNotes()
    }

    override fun makeNoteHearted(note: Note, hearted: Boolean) {
        mPresenter.makeNoteHearted(note, hearted)
    }

    override fun makeNoteFavourite(note: Note, favourite: Boolean) {
        mPresenter.makeNoteFavourite(note, favourite)
    }

    override fun onNotesUpdate(message: String) {
        Snackbar.make(coordinator, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        if (filterLayout.isRightSlideOpen) {
            filterLayout.toggleRightSlide()
        } else {
            super.onBackPressed()
        }

    }

}
