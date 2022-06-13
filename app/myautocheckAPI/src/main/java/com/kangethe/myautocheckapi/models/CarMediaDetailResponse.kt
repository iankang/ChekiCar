package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class CarMediaDetailResponse(
    @SerializedName("carMediaList")
    var carMediaList: List<CarMedia>? = null,
    @SerializedName("pagination")
    var pagination: CarMediaDetailPagination? = null
)