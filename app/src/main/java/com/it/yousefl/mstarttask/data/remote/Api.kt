package com.it.yousefl.mstarttask.data.remote


import com.it.yousefl.mstarttask.data.remote.response.DateModel
import com.it.yousefl.mstarttask.utils.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {

    @GET
    suspend fun getDate(@Url url:String): Response<DateModel>
}