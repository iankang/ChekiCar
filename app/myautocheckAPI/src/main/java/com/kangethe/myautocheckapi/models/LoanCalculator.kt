package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class LoanCalculator(
    @SerializedName("defaultValues")
    var defaultValues: DefaultValues? = null,
    @SerializedName("loanPercentage")
    var loanPercentage: Double? = null,
    @SerializedName("ranges")
    var ranges: Ranges? = null
)