package com.it.yousefl.estartatask.ui.main.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.it.yousefl.estartatask.data.remote.booksresponse.Book
import com.it.yousefl.estartatask.ui.main.booksrepository.BooksRepository
import com.it.yousefl.estartatask.utils.Event
import com.it.yousefl.estartatask.utils.Resource
import kotlinx.coroutines.delay

private const val TAG = "DateViewModel"

class BooksViewModel @ViewModelInject constructor(
    private val repository: BooksRepository
) : ViewModel() {

    private val _books = MutableLiveData<Event<Resource<List<Book>>>>()
    val books: LiveData<Event<Resource<List<Book>>>> = _books

    suspend fun getBooks() {
        _books.postValue(Event(Resource.loading(null)))
        delay(100)

        val response = repository.getBooks()

        _books.postValue(Event(response))


    }
}