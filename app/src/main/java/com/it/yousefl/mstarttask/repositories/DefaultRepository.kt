package com.it.yousefl.mstarttask.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.bumptech.glide.load.HttpException
import com.it.yousefl.mstarttask.data.local.EventDao
import com.it.yousefl.mstarttask.data.local.EventItem
import com.it.yousefl.mstarttask.data.remote.Api
import com.it.yousefl.mstarttask.data.remote.errorhandler.NetworkResponse
import com.it.yousefl.mstarttask.data.remote.response.DateModel
import com.it.yousefl.mstarttask.utils.Constants
import com.it.yousefl.mstarttask.utils.Resource
import javax.inject.Inject

private const val TAG = "DefaultRepository"
class DefaultRepository @Inject constructor(
    private val dao: EventDao,
    private val api: Api
) :EventRepository{
    override suspend fun convertDate(date: String): Resource<DateModel> {
        return try {
            val url=Constants.BASE_URL+"gToH?date=$date"
            Log.d(TAG, "convertDate: url=$url")
            val response=api.getDate(url)

            if (response.isSuccessful){
                response.body()?.let {
                    return@let  Resource.success(it)
                }?:
                Resource.error(NetworkResponse.instance.networHandler(response.body().let {
                    return@let it!!.code
                }).toString(), null)
            }else {
                Resource.error(NetworkResponse.instance.networHandler(response.body().let {
                    return@let it!!.code
                }).toString(), null)
            }
        }catch (exception: Exception){
            Resource.error("Could'nt fetch data ${exception.message}", null)
        }
    }

    override suspend fun insertEvent(eventItem: EventItem) {
      dao.insertEvent(eventItem)
    }

    override suspend fun deleteEvent(eventItem: EventItem) {
        dao.deleteEventItem(eventItem)
    }

    override suspend fun deleteAllEvents() {
        dao.deleteAllEvents()
    }

    override suspend fun update(eventItem: EventItem) {
        dao.update(eventItem)
    }

    override suspend fun deleteDataEvent(idList: List<Int>) {
        dao.deleteDataEvent(idList)
    }

    override fun observeAllEventItems(): LiveData<List<EventItem>> {
     return   dao.observeAllEventItems()
    }
}