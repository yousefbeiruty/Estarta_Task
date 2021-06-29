package com.it.yousefl.mstarttask.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_item")
data class EventItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var event_name:String,
    var event_description:String,
    var gregorian_date:String,
    var hijri:String,
    var serve_date_time:String
)