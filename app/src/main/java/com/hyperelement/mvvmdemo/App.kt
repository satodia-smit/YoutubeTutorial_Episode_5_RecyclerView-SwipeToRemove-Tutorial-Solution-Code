package com.hyperelement.mvvmdemo

import android.app.Application
import com.hyperelement.mvvmdemo.di.appModule
import com.hyperelement.mvvmdemo.di.networkModule
import com.hyperelement.mvvmdemo.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
* Created By: dev1618
* Created Date: 6/19/2019
* Purpose: Application level class which is used for the timber logging, koin starting for dependencies etc.
*/

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@App)
            modules(networkModule, retrofitModule, appModule)
        }

    }

}