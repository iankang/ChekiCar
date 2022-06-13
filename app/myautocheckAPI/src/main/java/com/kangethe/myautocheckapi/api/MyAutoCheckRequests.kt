package com.kangethe.myautocheckapi.api

import com.kangethe.myautocheckapi.models.CarDetailResponse
import com.kangethe.myautocheckapi.models.CarListResponse
import com.kangethe.myautocheckapi.models.MakesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyAutoCheckRequests {

    @GET("make")
    suspend fun getPopularMakes(
        @Query("popular") popular: Boolean
    ): Response<MakesListResponse>

    @GET("car/search")
    suspend fun getCarList(
        @Query("pageSize") pageSize: Int? = 40,
        @Query("page_number") page_number: Int? = 0
    ): Response<CarListResponse>

    @GET("car/{carID}")
    suspend fun getCarDetails(
        @Path("carID") carID:String,
        @Query("pageSize") pageSize: Int? = 40,
        @Query("page_number") page_number: Int? = 0
    ):Response<CarDetailResponse>
}