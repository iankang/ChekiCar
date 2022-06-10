package com.kangethe.myautocheckapi.di

import android.content.Context
import android.os.Build
import com.kangethe.myautocheckapi.api.MyAutoCheckRequests
import com.kangethe.myautocheckapi.utils.Constants.MY_AUTO_CHECK_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

val myAutoCheckModule = module {
    factory { provideOkHttpClientInterceptor() }
    factory { provideOkHttpClient(get(), get()) }
    factory { provideRetrofit(get()) }
//    factory { interceptor }
    single { provideMyAutoCheckAPI(get()) }

}


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(MY_AUTO_CHECK_BASE_URL)
        .build()
}

fun provideOkHttpClientInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

fun provideOkHttpClient(
    context: Context,
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(Duration.ofMinutes(2))
            .build()
    } else {
        TODO("VERSION.SDK_INT < O")
    }
}

fun provideMyAutoCheckAPI(retrofit: Retrofit): MyAutoCheckRequests {
    return retrofit.create(MyAutoCheckRequests::class.java)
}