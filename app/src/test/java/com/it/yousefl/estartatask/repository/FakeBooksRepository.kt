package com.it.yousefl.estartatask.repository

import androidx.lifecycle.MutableLiveData
import com.it.yousefl.estartatask.data.remote.booksresponse.Book
import com.it.yousefl.estartatask.ui.main.mainrepository.BooksRepository
import com.it.yousefl.estartatask.utils.Resource

class FakeBooksRepository : BooksRepository {
    private val eventItems = mutableListOf<Book>()

    private val observableEventItems = MutableLiveData<List<Book>>(eventItems)


    private var shouldReturnANetworkError = false

    fun setshouldReturnANetworkError(value: Boolean) {
        shouldReturnANetworkError = value
    }

    private fun refreshLiveData() {
        observableEventItems.postValue(eventItems)
    }

//    override suspend fun convertDate(date: String): Resource<DateModel> {
//
//        return if (shouldReturnANetworkError) {
//            Resource.error("Error", null)
//        } else {
//            Resource.success(
//                DateModel(
//                    200,
//                    Data(
//                        Gregorian(
//                            "date",
//                            "day",
//                            Designation(
//                                "abbreviated",
//                                "expanded"
//                            ),
//                            "format",
//                            Month(
//                                "en",
//                                1
//                            ),
//                            Weekday("en"),
//                            "year"
//                        ),
//                        Hijri(
//                            "date",
//                            "day",
//                            DesignationX(
//                                "abbreviated",
//                                "expanded"
//                            ),
//                            "format",
//                            ArrayList<String>(),
//                            MonthX(
//                                "ar",
//                                "en",
//                                1
//                            ),
//                            WeekdayX(
//                                "ar",
//                                "en"
//                            ),
//                            "year"
//                        )
//                    ),
//                    ""
//                )
//            )
//        }
//    }
//
//    override suspend fun insertEvent(eventItem: EventItem) {
//       eventItems.add(eventItem)
//        refreshLiveData()
//    }
//
//    override suspend fun deleteEvent(eventItem: EventItem) {
//        eventItems.remove(eventItem)
//        refreshLiveData()
//    }
//
//    override suspend fun deleteAllEvents() {
//        eventItems.clear()
//        refreshLiveData()
//    }
//
//    override suspend fun update(eventItem: EventItem) {
//       eventItems.remove(eventItem)
//        refreshLiveData()
//    }
//
//    override suspend fun deleteDataEvent(idList: List<Int>) {
//        idList.map {
//            eventItems.map { event->
//                if(it==event.id)
//                    eventItems.remove(event)
//            }
//        }
//        refreshLiveData()
//    }
//
//    override fun observeAllEventItems(): LiveData<List<EventItem>> {
//        return observableEventItems
//    }

    override suspend fun getBooks(): Resource<List<Book>> {
        TODO("Not yet implemented")


    }
}