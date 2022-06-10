package com.kangethe.myautocheckapi.repository

import com.kangethe.myautocheckapi.api.MyAutoCheckRequests
import com.kangethe.myautocheckapi.api.myAutoCheckApiCall
import com.kangethe.myautocheckapi.models.MakesListResponse
import com.kangethe.myautocheckapi.models.MyAutoCheckResponse
import org.koin.java.KoinJavaComponent.inject

class MyAutoCheckAPI {
    private val myAutoCheckRequests: MyAutoCheckRequests by inject(MyAutoCheckRequests::class.java)
    private val TAG = MyAutoCheckAPI::class.java.name


    suspend fun getPopularMakes():MyAutoCheckResponse<MakesListResponse> {
        return  myAutoCheckApiCall(apiCall = {myAutoCheckRequests.getPopularMakes(true)})
    }

}