package com.android.r.ui

import android.app.Application
import android.util.Log
import com.android.r.di.appModules
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.startKoin


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(applicationContext, appModules)
        AndroidThreeTen.init(this)
    }
}