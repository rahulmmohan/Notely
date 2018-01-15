package com.rahul.notely.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*


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

    private fun msToDate(ms: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault())

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = ms
        return formatter.format(calendar.time)
    }
}