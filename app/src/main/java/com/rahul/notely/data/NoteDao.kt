package com.rahul.notely.data

import android.arch.persistence.room.*


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

    @Update
    fun update(note: Note)
}