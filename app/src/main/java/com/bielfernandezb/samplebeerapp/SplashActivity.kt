package com.bielfernandezb.samplebeerapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bielfernandezb.samplebeerapp.beer_list.views.activities.MainActivity
import com.bielfernandezb.samplebeerapp.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {

    lateinit var binding: ActivitySplashBinding
    var progressBeer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        scheduleSplashScreen(this)
    }

    private fun scheduleSplashScreen(context: Context) {
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    while (progressBeer <= 100) {
                        progressBeer += 20
                        sleep(1000)
                        binding.progress = progressBeer
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    moveOn(context)
                }
            }
        }

        thread.start()

    }

    private fun moveOn(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        finish()
    }

}