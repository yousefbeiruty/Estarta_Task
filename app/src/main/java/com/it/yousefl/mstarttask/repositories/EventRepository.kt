package com.it.yousefl.mstarttask.repositories

import androidx.lifecycle.LiveData
import com.it.yousefl.mstarttask.data.local.EventItem
import com.it.yousefl.mstarttask.data.remote.response.DateModel
import com.it.yousefl.mstarttask.utils.Resource
import retrofit2.http.Url

interface EventRepository {

    suspend fun convertDate(date:String): Resource<DateModel>

    suspend fun insertEvent(eventItem: EventItem)

    suspend fun deleteEvent(eventItem: EventItem)

    suspend fun deleteAllEvents()

    suspend fun update(eventItem: EventItem)

    suspend fun deleteDataEvent(idList: List<Int>)

    fun observeAllEventItems(): LiveData<List<EventItem>>
}