package com.rahul.notely

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rahul.notely.notes.NotesActivity
import kotlinx.android.synthetic.main.notes_layout.*
import kotlinx.android.synthetic.main.activity_notes.*

/**
 * Created by rahul on 15/01/18.
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, NotesActivity::class.java)
        startActivity(intent)
        // close this activity
        finish()

    }

}
