package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class MakesListResponse(
    @SerializedName("makeList")
    var makeList: List<Make>? = null,
    @SerializedName("pagination")
    var pagination: Pagination? = null
)