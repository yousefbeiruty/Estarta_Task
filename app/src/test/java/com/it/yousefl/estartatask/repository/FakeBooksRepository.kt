package com.it.yousefl.estartatask.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.annotations.SerializedName
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


    override suspend fun getBooks(): Resource<List<Book>> {
        val s = mutableListOf<String>()
        return if (shouldReturnANetworkError) {
            Resource.error("Error", null)
        } else {
            val m = mutableListOf<Book>()
            m.add(
                Book("Date", s, s, s, "Name", "Price", "UID")
            )
            Resource.success(m)
        }
    }
}