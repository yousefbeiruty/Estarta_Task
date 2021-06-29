package com.it.yousefl.mstarttask.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(eventItem: EventItem)

    @Delete
    suspend fun deleteEventItem(eventItem: EventItem)

    @Query("DELETE FROM event_item")
    suspend fun deleteAllEvents()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(eventItem: EventItem)

    @Query("delete from event_item where id in (:idList)")
    suspend fun deleteDataEvent(idList: List<Int>)

    //TODO:-we didn't use suspend function because it returns a liveData object
    @Query("SELECT * FROM event_item")
    fun observeAllEventItems(): LiveData<List<EventItem>>
}