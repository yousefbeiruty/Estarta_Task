package com.it.yousefl.estartatask.ui.main.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.it.yousefl.estartatask.data.remote.booksresponse.Book
import com.it.yousefl.estartatask.ui.main.mainrepository.BooksRepository
import com.it.yousefl.estartatask.utils.Event
import com.it.yousefl.estartatask.utils.Resource
import kotlinx.coroutines.delay

private const val TAG = "DateViewModel"

class DateViewModel @ViewModelInject constructor(
    private val repository: BooksRepository
) : ViewModel() {

    private val _books=MutableLiveData<Event<Resource<List<Book>>>>()
    val books: LiveData<Event<Resource<List<Book>>>> = _books

    suspend fun getBooks(){
        _books.postValue(Event(Resource.loading(null)))
        delay(100)
        Log.d(TAG, "getBooks: ")
        Log.d(TAG, "getBooks:  _books.value= ${repository.getBooks().data}")
          //  _books.postValue(Event(Resource.loading(null)))
            val response =  repository.getBooks()
        Log.d(TAG, "getBooks:  _books.value= ${_books.value}")
            _books.postValue(Event(response))
            Log.d(TAG, "getBooks:  _books.value= ${_books.value}")

    }
}