package com.rahul.notely.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "Notes")
class Note {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "title")
    var title: String = ""

    @ColumnInfo(name = "details")
    var details: String = ""

    @ColumnInfo(name = "date")
    var date: Long = 0

    @ColumnInfo(name = "hearted")
    var hearted: Boolean = false

    @ColumnInfo(name = "favourite")
    var favourite: Boolean = false

}