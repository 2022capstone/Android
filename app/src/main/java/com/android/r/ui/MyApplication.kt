package com.android.r.ui

import android.app.Application
import android.util.Log
import com.android.r.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(applicationContext)
            modules(appModules)
            Log.d("KOINN", "Koin Start")
        }
    }
}