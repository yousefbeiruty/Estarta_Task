package com.it.yousefl.estartatask.data.remote


import com.it.yousefl.estartatask.data.remote.booksresponse.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {
    @GET
    suspend fun getBooks(@Url url:String=""):Response<Result>
}