package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("bodyTypeId")
    var bodyTypeId: String? = null,
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("depositReceived")
    var depositReceived: Boolean? = null,
    @SerializedName("fuelType")
    var fuelType: String? = null,
    @SerializedName("gradeScore")
    var gradeScore: Double? = null,
    @SerializedName("hasFinancing")
    var hasFinancing: Boolean? = null,
    @SerializedName("hasThreeDImage")
    var hasThreeDImage: Boolean? = null,
    @SerializedName("hasWarranty")
    var hasWarranty: Boolean? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("imageUrl")
    var imageUrl: String? = null,
    @SerializedName("installment")
    var installment: Float? = null,
    @SerializedName("licensePlate")
    var licensePlate: String? = null,
    @SerializedName("loanValue")
    var loanValue: Float? = null,
    @SerializedName("marketplaceOldPrice")
    var marketplaceOldPrice: Int? = null,
    @SerializedName("marketplacePrice")
    var marketplacePrice: Int? = null,
    @SerializedName("marketplaceVisibleDate")
    var marketplaceVisibleDate: String? = null,
    @SerializedName("mileage")
    var mileage: Int? = null,
    @SerializedName("mileageUnit")
    var mileageUnit: String? = null,
    @SerializedName("sellingCondition")
    var sellingCondition: String? = null,
    @SerializedName("sold")
    var sold: Boolean? = null,
    @SerializedName("state")
    var state: String? = null,
    @SerializedName("stats")
    var stats: Stats? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("transmission")
    var transmission: String? = null,
    @SerializedName("websiteUrl")
    var websiteUrl: String? = null,
    @SerializedName("year")
    var year: Int? = null
)