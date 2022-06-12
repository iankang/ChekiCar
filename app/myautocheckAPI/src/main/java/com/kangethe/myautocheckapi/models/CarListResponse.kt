package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class CarListResponse(
    @SerializedName("pagination")
    var pagination: CarListPagination? = null,
    @SerializedName("result")
    var result: List<Result>? = null
)