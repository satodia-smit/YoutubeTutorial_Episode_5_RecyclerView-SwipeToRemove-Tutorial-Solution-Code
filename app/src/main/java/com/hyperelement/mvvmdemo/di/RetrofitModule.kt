package com.hyperelement.mvvmdemo.di

import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created By: dev1618
 * Created Date: 6/19/2019
 * Purpose: KOIN Module provides retrofit related services which can
 * provides the various required API Interfaces.
 */

val retrofitModule = module {

    single { provideRetrofit(get(), get()) }
}

/**
 * Created By: dev1618
 * Created Date: 6/19/2019
 * Purpose: Function to provide retrofit instance.
 */

fun provideRetrofit(gson: Gson, httpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
        .baseUrl("https://abc.xyz")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}