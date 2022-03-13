package com.it.yousefl.estartatask.data.remote.booksresponse


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("pagination")
    val pagination: Pagination? = null,
    @SerializedName("results")
    val results: List<ResultX>? = null
)