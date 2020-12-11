package com.hyperelement.mvvmdemo.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hyperelement.mvvmdemo.utilities.AppUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideGson() }
    single { provideCache(androidContext()) }
    single { provideHttpClient(androidContext(), get()) }
}

// This variable define 10 MB of cache size.
const val cacheSize = (10 * 1024 * 1024).toLong()

/**
 *  This method will provide the instance of Gson.
 */
fun provideGson(): Gson {
    return GsonBuilder()
        .create()
}

/**
 * This method will provide the cache instance with size of 10 MB
 */
fun provideCache(context: Context): Cache = Cache(context.cacheDir, cacheSize)


/**
 *  this method will provide the okhttpclient instance for API.
 */
fun provideHttpClient(
    context: Context,
    cache: Cache
): OkHttpClient {


    return OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor { chain ->
            val request = chain.request()
            val requestBuilder = request.newBuilder()

            if (AppUtils.hasNetwork(context)) {
                requestBuilder.header("Cache-Control", "public, max-age=" + 5)
            } else {
                requestBuilder.header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                )
            }

            return@addInterceptor chain.proceed(requestBuilder.build())
        }
        .addInterceptor(HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
}

