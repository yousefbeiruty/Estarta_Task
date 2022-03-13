package com.it.yousefl.estartatask.ui.main.mainrepository

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
            Log.d(TAG, "getBooks: response.body()?.books")
            if(response.isSuccessful){
                return Resource.success(response.body()?.books)
           }
            return Resource.error("Could'nt fetch data",null)
        }catch (exception: Exception){
            Log.d(TAG, "getBooks: Could'nt fetch data ${exception.message}")
            Resource.error("Could'nt fetch data ${exception.message}", null)
        }
    }
}