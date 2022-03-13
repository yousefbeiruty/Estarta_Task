package com.it.yousefl.mstarttask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.annotations.SerializedName
import com.it.yousefl.mstarttask.data.local.EventItem
import com.it.yousefl.mstarttask.data.remote.response.*
import com.it.yousefl.mstarttask.repositories.EventRepository
import com.it.yousefl.mstarttask.utils.Resource

class FakeEventRepository : EventRepository {
    private val eventItems = mutableListOf<EventItem>()

    private val observableEventItems = MutableLiveData<List<EventItem>>(eventItems)


    private var shouldReturnANetworkError = false

    fun setshouldReturnANetworkError(value: Boolean) {
        shouldReturnANetworkError = value
    }

    private fun refreshLiveData() {
        observableEventItems.postValue(eventItems)
    }

    override suspend fun convertDate(date: String): Resource<DateModel> {

        return if (shouldReturnANetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(
                DateModel(
                    200,
                    Data(
                        Gregorian(
                            "date",
                            "day",
                            Designation(
                                "abbreviated",
                                "expanded"
                            ),
                            "format",
                            Month(
                                "en",
                                1
                            ),
                            Weekday("en"),
                            "year"
                        ),
                        Hijri(
                            "date",
                            "day",
                            DesignationX(
                                "abbreviated",
                                "expanded"
                            ),
                            "format",
                            ArrayList<String>(),
                            MonthX(
                                "ar",
                                "en",
                                1
                            ),
                            WeekdayX(
                                "ar",
                                "en"
                            ),
                            "year"
                        )
                    ),
                    ""
                )
            )
        }
    }

    override suspend fun insertEvent(eventItem: EventItem) {
       eventItems.add(eventItem)
        refreshLiveData()
    }

    override suspend fun deleteEvent(eventItem: EventItem) {
        eventItems.remove(eventItem)
        refreshLiveData()
    }

    override suspend fun deleteAllEvents() {
        eventItems.clear()
        refreshLiveData()
    }

    override suspend fun update(eventItem: EventItem) {
       eventItems.remove(eventItem)
        refreshLiveData()
    }

    override suspend fun deleteDataEvent(idList: List<Int>) {
        idList.map {
            eventItems.map { event->
                if(it==event.id)
                    eventItems.remove(event)
            }
        }
        refreshLiveData()
    }

    override fun observeAllEventItems(): LiveData<List<EventItem>> {
        return observableEventItems
    }
}