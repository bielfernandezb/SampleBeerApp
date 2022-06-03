package com.bielfernandezb.samplebeerapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleBeerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}