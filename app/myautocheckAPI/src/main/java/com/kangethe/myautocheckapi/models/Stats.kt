package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("appViewCount")
    var appViewCount: Int? = null,
    @SerializedName("appViewerCount")
    var appViewerCount: Int? = null,
    @SerializedName("interestCount")
    var interestCount: Int? = null,
    @SerializedName("processedLoanCount")
    var processedLoanCount: Int? = null,
    @SerializedName("testDriveCount")
    var testDriveCount: Int? = null,
    @SerializedName("webViewCount")
    var webViewCount: Int? = null,
    @SerializedName("webViewerCount")
    var webViewerCount: Int? = null
)