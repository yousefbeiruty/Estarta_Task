package com.it.yousefl.estartatask.ui.main.booksrepository

import android.util.Log
import com.it.yousefl.estartatask.data.remote.Api
import com.it.yousefl.estartatask.data.remote.booksresponse.Book
import com.it.yousefl.estartatask.utils.Resource
import javax.inject.Inject

private const val TAG = "DefaultRepository"
class DefaultRepository @Inject constructor(
    private val api: Api
) :BooksRepository{
    override suspend fun getBooks():Resource<List<Book>> {
        return try {
            val response=api.getBooks()

            if(response.isSuccessful){
                return Resource.success(response.body()?.books)
           }
            return Resource.error("Could'nt fetch data",null)
        }catch (exception: Exception){
            Resource.error("Could'nt fetch data ${exception.message}", null)
        }
    }
}