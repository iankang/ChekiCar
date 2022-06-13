package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class Ranges(
    @SerializedName("maxDownPayment")
    var maxDownPayment: Double? = null,
    @SerializedName("maxInterestRate")
    var maxInterestRate: Double? = null,
    @SerializedName("minDownPayment")
    var minDownPayment: Double? = null,
    @SerializedName("minInterestRate")
    var minInterestRate: Double? = null,
    @SerializedName("tenure")
    var tenure: Int? = null
)