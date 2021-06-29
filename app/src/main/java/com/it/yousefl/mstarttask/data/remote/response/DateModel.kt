package com.it.yousefl.mstarttask.data.remote.response


import com.google.gson.annotations.SerializedName

data class DateModel(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
)