package com.rahul.notely

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rahul.notely.composenote.NoteComposeActivity

/**
 * Created by rahul on 15/01/18.
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, NoteComposeActivity::class.java)
        startActivity(intent)
        // close this activity
        finish()
    }

}
