package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class CarMediaDetailPagination(
    @SerializedName("currentPage")
    var currentPage: Int? = null,
    @SerializedName("pageSize")
    var pageSize: Int? = null,
    @SerializedName("total")
    var total: Int? = null
)