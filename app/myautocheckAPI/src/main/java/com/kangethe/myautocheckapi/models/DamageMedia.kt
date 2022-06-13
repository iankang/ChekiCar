package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class DamageMedia(
    @SerializedName("inspectionItems")
    var inspectionItems: List<InspectionItem>? = null,
    @SerializedName("name")
    var name: String? = null
)