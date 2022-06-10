package com.kangethe.myautocheckapi

import android.app.Application
import com.kangethe.myautocheckapi.di.repositoryModule
import com.kangethe.myautocheckapi.di.myAutoCheckModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class MyAutoCheckAPI:Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)
            modules(
                listOf(
                    myAutoCheckModule,
                    repositoryModule
                )
            )
        }
    }
}