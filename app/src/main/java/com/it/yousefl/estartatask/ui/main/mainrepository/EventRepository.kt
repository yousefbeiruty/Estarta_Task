package com.it.yousefl.estartatask.repositories

import androidx.lifecycle.LiveData
import com.it.yousefl.estartatask.data.local.EventItem
import com.it.yousefl.estartatask.data.remote.response.DateModel
import com.it.yousefl.estartatask.utils.Resource

interface EventRepository {

    suspend fun convertDate(date:String): Resource<DateModel>

    suspend fun insertEvent(eventItem: EventItem)

    suspend fun deleteEvent(eventItem: EventItem)

    suspend fun deleteAllEvents()

    suspend fun update(eventItem: EventItem)

    suspend fun deleteDataEvent(idList: List<Int>)

    fun observeAllEventItems(): LiveData<List<EventItem>>
}