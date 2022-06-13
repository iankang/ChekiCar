package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class CarMedia(
    @SerializedName("createdAt")
    var createdAt: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("url")
    var url: String? = null
)