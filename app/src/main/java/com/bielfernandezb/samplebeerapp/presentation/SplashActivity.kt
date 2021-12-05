package com.bielfernandezb.samplebeerapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bielfernandezb.samplebeerapp.databinding.ActivitySplashBinding
import com.bielfernandezb.samplebeerapp.presentation.navigation.Navigator

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    private var progressBeer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        scheduleSplashScreen(this)
    }

    private fun scheduleSplashScreen(currentActivity: AppCompatActivity) {
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    while (progressBeer <= 100) {
                        progressBeer += 20
                        sleep(1000)
                        binding.beerProgressView.beerProgress = progressBeer
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    Navigator().navigateToMainActivity(currentActivity)
                }
            }
        }

        thread.start()

    }

}