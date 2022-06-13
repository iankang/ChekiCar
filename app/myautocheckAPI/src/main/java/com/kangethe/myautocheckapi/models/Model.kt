package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("imageUrl")
    var imageUrl: String? = null,
    @SerializedName("make")
    var make: CarDetailMake? = null,
    @SerializedName("modelFeatures")
    var modelFeatures: List<Any>? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("popular")
    var popular: Boolean? = null,
    @SerializedName("wheelType")
    var wheelType: String? = null
)