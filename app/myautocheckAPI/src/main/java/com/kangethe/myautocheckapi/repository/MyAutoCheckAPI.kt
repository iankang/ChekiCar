package com.kangethe.myautocheckapi.repository

import com.kangethe.myautocheckapi.api.MyAutoCheckRequests
import com.kangethe.myautocheckapi.api.myAutoCheckApiCall
import com.kangethe.myautocheckapi.models.*
import org.koin.java.KoinJavaComponent.inject

class MyAutoCheckAPI {
    private val myAutoCheckRequests: MyAutoCheckRequests by inject(MyAutoCheckRequests::class.java)
    private val TAG = MyAutoCheckAPI::class.java.name


    suspend fun getPopularMakes(): MyAutoCheckResponse<MakesListResponse> {
        return myAutoCheckApiCall(apiCall = { myAutoCheckRequests.getPopularMakes(true) })
    }

    suspend fun getCarsList(
        pageSize: Int?,
        page: Int?
    ): MyAutoCheckResponse<CarListResponse> {
        return myAutoCheckApiCall(apiCall = { myAutoCheckRequests.getCarList(pageSize, page) })
    }

    suspend fun getCarDetails(
        carId: String?,
        pageSize: Int?,
        page: Int?
    ): MyAutoCheckResponse<CarDetailResponse> {
        return myAutoCheckApiCall(apiCall = {
            myAutoCheckRequests.getCarDetails(
                carId!!,
                pageSize,
                page
            )
        })
    }

    suspend fun getCarDetailsMedia(
        carId: String?,
        pageSize: Int?,
        page: Int?
    ): MyAutoCheckResponse<CarMediaDetailResponse> {
        return myAutoCheckApiCall(apiCall = {
            myAutoCheckRequests.getCarDetailMedia(
                carId!!,
                pageSize,
                page
            )
        })
    }
}