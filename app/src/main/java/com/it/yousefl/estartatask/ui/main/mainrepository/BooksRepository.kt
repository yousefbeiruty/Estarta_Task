package com.it.yousefl.estartatask.ui.main.mainrepository


import com.it.yousefl.estartatask.data.remote.booksresponse.Book
import com.it.yousefl.estartatask.utils.Resource

interface BooksRepository {
    suspend fun getBooks(): Resource<List<Book>>
}