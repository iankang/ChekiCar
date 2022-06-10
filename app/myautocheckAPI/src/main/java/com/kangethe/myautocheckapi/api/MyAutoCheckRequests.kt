package com.kangethe.myautocheckapi.api

import com.kangethe.myautocheckapi.models.MakesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyAutoCheckRequests {

    @GET("make")
    suspend fun getPopularMakes(
        @Query("popular") popular: Boolean
    ): Response<MakesListResponse>
}