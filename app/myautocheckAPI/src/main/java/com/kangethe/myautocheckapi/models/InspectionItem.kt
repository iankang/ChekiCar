package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class InspectionItem(
    @SerializedName("comment")
    var comment: String? = null,
    @SerializedName("condition")
    var condition: String? = null,
    @SerializedName("medias")
    var medias: List<Media>? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("response")
    var response: String? = null
)