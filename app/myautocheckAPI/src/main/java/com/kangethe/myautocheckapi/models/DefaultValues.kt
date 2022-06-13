package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class DefaultValues(
    @SerializedName("downPayment")
    var downPayment: Double? = null,
    @SerializedName("interestRate")
    var interestRate: Double? = null,
    @SerializedName("tenure")
    var tenure: Int? = null
)