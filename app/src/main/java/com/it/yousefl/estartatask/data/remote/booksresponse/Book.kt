package com.it.yousefl.estartatask.data.remote.booksresponse


import com.google.gson.annotations.SerializedName

data class ResultX(
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("image_ids")
    val imageIds: List<String>? = null,
    @SerializedName("image_urls")
    val imageUrls: List<String>? = null,
    @SerializedName("image_urls_thumbnails")
    val imageUrlsThumbnails: List<String>? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("price")
    val price: String? = null,
    @SerializedName("uid")
    val uid: String? = null
)