package com.it.yousefl.estartatask.ui.main.booksrepository


import com.it.yousefl.estartatask.data.remote.booksresponse.Book
import com.it.yousefl.estartatask.utils.Resource

interface BooksRepository {
    suspend fun getBooks(): Resource<List<Book>>
}