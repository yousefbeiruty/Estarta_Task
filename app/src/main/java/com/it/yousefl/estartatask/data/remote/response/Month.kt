package com.it.yousefl.mstarttask.data.remote.response


import com.google.gson.annotations.SerializedName

data class Month(
    @SerializedName("en")
    val en: String,
    @SerializedName("number")
    val number: Int
)