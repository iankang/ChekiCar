package com.kangethe.myautocheckapi.models

data class MyAutoCheckResponse<T>(
    var data: T? = null,
    var message :String= "fail",
    var isOk: Boolean = false,
    var httpStatus:Int = 0
)
