package com.rahul.notely.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query



/**
 * Created by Rahul on 15/01/18.
 */
@Dao interface NoteDao {

    @Query("SELECT * FROM Notes")
    fun getAll(): List<Note>

    @Insert
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)
}