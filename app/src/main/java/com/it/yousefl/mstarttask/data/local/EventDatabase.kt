package com.it.yousefl.mstarttask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EventItem::class],version = 1)
abstract class EventDatabase :RoomDatabase(){
    abstract fun eventDao():EventDao
}