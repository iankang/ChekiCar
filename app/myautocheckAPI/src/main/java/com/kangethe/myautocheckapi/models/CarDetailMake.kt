package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class CarDetailMake(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("imageUrl")
    var imageUrl: String? = null,
    @SerializedName("name")
    var name: String? = null
)