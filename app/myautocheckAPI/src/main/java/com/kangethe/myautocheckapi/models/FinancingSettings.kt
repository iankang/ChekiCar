package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class FinancingSettings(
    @SerializedName("loanCalculator")
    var loanCalculator: LoanCalculator? = null
)