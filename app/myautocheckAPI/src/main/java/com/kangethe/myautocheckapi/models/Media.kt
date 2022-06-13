package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("url")
    var url: String? = null
)