package com.kangethe.myautocheckapi.api

import android.util.Log
import com.kangethe.myautocheckapi.models.MyAutoCheckResponse
import kotlinx.coroutines.*
import retrofit2.Response
import java.io.IOException

private val TAG: String = "RequestMaker"
suspend fun <T> myAutoCheckApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> Response<T>
): MyAutoCheckResponse<T> {
    return withContext(dispatcher) {
        try {
            val responseDeffered: Deferred<Response<T>> = async { apiCall.invoke() }
            val response = responseDeffered.await()
            Log.d(TAG, "response: ${response.raw()}")
            if (response.isSuccessful) {
                Log.d(TAG, "success")
                MyAutoCheckResponse(
                    data = response.body(),
                    message = "successful",
                    isOk = true,
                    httpStatus = response.code()
                )
            } else {
                Log.e(TAG, "error: ${response}")
                MyAutoCheckResponse(
                    data = response.body(),
                    message = response.errorBody().toString(),
                    isOk = false,
                    httpStatus = response.code()
                )
            }

        } catch (error: IOException) {

            MyAutoCheckResponse(message = error.localizedMessage)
        }
    }
}