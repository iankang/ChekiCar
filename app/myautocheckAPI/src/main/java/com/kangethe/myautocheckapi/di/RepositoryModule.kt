package com.kangethe.myautocheckapi.di

import com.kangethe.myautocheckapi.repository.MyAutoCheckAPI
import org.koin.dsl.module

val repositoryModule = module {
    single { provideGlobalMyAutoCheckAPI()  }
}

fun provideGlobalMyAutoCheckAPI(): MyAutoCheckAPI {
    return MyAutoCheckAPI()
}