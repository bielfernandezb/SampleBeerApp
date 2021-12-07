package com.bielfernandezb.samplebeerapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleBeerApplication : Application() {
    private var mInstance: SampleBeerApplication? = null

    @Synchronized
    fun getInstance(): SampleBeerApplication? {
        return mInstance
    }

    override fun onCreate() {
        mInstance = this
        super.onCreate()
    }
}