package com.it.yousefl.mstarttask.ui.main.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.it.yousefl.mstarttask.data.local.EventItem
import com.it.yousefl.mstarttask.data.remote.response.DateModel
import com.it.yousefl.mstarttask.repositories.EventRepository
import com.it.yousefl.mstarttask.utils.Event
import com.it.yousefl.mstarttask.utils.Resource
import kotlinx.coroutines.launch

private const val TAG = "DateViewModel"
class DateViewModel @ViewModelInject constructor(
    private val repository: EventRepository
) : ViewModel() {

    val eventItems = repository.observeAllEventItems()

    private val _date= MutableLiveData<Event<Resource<DateModel>>>()
    val date: LiveData<Event<Resource<DateModel>>> = _date

    private val _insertEventStatus = MutableLiveData<Event<Resource<EventItem>>>()
    val insertEventStatus: LiveData<Event<Resource<EventItem>>> =
        _insertEventStatus

    private val _eventsIds=MutableLiveData<ArrayList<Int>>()
    val eventsIds:LiveData<ArrayList<Int>> = _eventsIds
    val idList=ArrayList<Int>()

    fun addEventId(id:Int){
        idList.add(id)
        _eventsIds.value=idList
    }
    fun removeEventId(id:Int){
        idList.remove(id)
        _eventsIds.value=idList
    }

    fun getIds():LiveData<ArrayList<Int>>{
        return eventsIds
    }


    fun convert(date: String) {
        if (date.isEmpty()){
            return
        }

        _date.value= Event(Resource.loading(null))
        viewModelScope.launch {
            val response =  repository.convertDate(date)
            _date.value=Event(response)
            Log.d(TAG, "convert:  _date.value= ${_date.value}")
        }
    }

    fun insertEventItem(event_name: String, event_description: String, gregorian_date: String,
                           hijri: String,serve_date_time:String) {

        val eventItem =
            EventItem(event_name = event_name,event_description = event_description,
            gregorian_date = gregorian_date,hijri = hijri,serve_date_time = serve_date_time)

        Log.d(TAG, "insertEventItem: eventItem= $eventItem")
        insertEventItemIntoDb(eventItem)

        _insertEventStatus.postValue(Event(Resource.success(eventItem)))

    }

    fun insertEventItemIntoDb(eventItem: EventItem) = viewModelScope.launch {
        repository.insertEvent(eventItem)
    }

    fun deleteEventItem(eventItem: EventItem) = viewModelScope.launch {
        repository.deleteEvent(eventItem)
    }

     fun deleteAllEventsItems()=viewModelScope.launch{
        repository.deleteAllEvents()
    }

    fun update(eventItem: EventItem)=viewModelScope.launch {
        repository.update(eventItem)
        repository.observeAllEventItems()
    }
    fun deleteDataEventsItems(idList: ArrayList<Int>)=viewModelScope.launch{
        repository.deleteDataEvent(idList)
    }

}