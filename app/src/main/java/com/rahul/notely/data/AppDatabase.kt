package com.rahul.notely.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.content.Context


/**
 * Created by rahul on 15/01/18.
 */
@Database(entities = [(Note::class)], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        lateinit  var INSTANCE: AppDatabase
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE
        }
    }
}