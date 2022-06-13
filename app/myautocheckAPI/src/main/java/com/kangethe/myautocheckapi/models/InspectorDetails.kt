package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class InspectorDetails(
    @SerializedName("inspectedMakes")
    var inspectedMakes: List<Any>? = null,
    @SerializedName("totalInspection")
    var totalInspection: Int? = null
)