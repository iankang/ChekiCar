package com.kangethe.myautocheckapi.models


import com.google.gson.annotations.SerializedName

data class CarDetailResponse(
    @SerializedName("bodyType")
    var bodyType: BodyType? = null,
    @SerializedName("carFeatures")
    var carFeatures: List<Any>? = null,
    @SerializedName("carName")
    var carName: String? = null,
    @SerializedName("category")
    var category: Category? = null,
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("createdAt")
    var createdAt: String? = null,
    @SerializedName("damageMedia")
    var damageMedia: List<DamageMedia>? = null,
    @SerializedName("depositReceived")
    var depositReceived: Boolean? = null,
    @SerializedName("engineType")
    var engineType: String? = null,
    @SerializedName("exteriorColor")
    var exteriorColor: String? = null,
    @SerializedName("features")
    var features: List<Any>? = null,
    @SerializedName("financingSettings")
    var financingSettings: FinancingSettings? = null,
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
    @SerializedName("inspectorDetails")
    var inspectorDetails: InspectorDetails? = null,
    @SerializedName("installment")
    var installment: Number? = null,
    @SerializedName("insured")
    var insured: Boolean? = null,
    @SerializedName("interiorColor")
    var interiorColor: String? = null,
    @SerializedName("isFeatured")
    var isFeatured: Boolean? = null,
    @SerializedName("loanValue")
    var loanValue: Double? = null,
    @SerializedName("marketplaceOldPrice")
    var marketplaceOldPrice: Int? = null,
    @SerializedName("marketplacePrice")
    var marketplacePrice: Int? = null,
    @SerializedName("marketplaceVisible")
    var marketplaceVisible: Boolean? = null,
    @SerializedName("marketplaceVisibleDate")
    var marketplaceVisibleDate: String? = null,
    @SerializedName("mileage")
    var mileage: Int? = null,
    @SerializedName("mileageUnit")
    var mileageUnit: String? = null,
    @SerializedName("model")
    var model: Model? = null,
    @SerializedName("modelFeatures")
    var modelFeatures: List<Any>? = null,
    @SerializedName("ownerType")
    var ownerType: String? = null,
    @SerializedName("sellingCondition")
    var sellingCondition: String? = null,
    @SerializedName("sold")
    var sold: Boolean? = null,
    @SerializedName("state")
    var state: String? = null,
    @SerializedName("transmission")
    var transmission: String? = null,
    @SerializedName("updatedAt")
    var updatedAt: String? = null,
    @SerializedName("vin")
    var vin: String? = null,
    @SerializedName("websiteUrl")
    var websiteUrl: String? = null,
    @SerializedName("year")
    var year: Int? = null
)