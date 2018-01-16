package com.rahul.notely.composenote

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.rahul.notely.R
import kotlinx.android.synthetic.main.activity_note_detail.*

class NoteComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_compose)
        setSupportActionBar(toolbar)

    }
}
