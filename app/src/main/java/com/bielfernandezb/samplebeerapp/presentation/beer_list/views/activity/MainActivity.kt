package com.bielfernandezb.samplebeerapp.presentation.beer_list.views.activity

import android.os.Bundle
import com.bielfernandezb.samplebeerapp.R
import com.bielfernandezb.samplebeerapp.presentation.BaseActivity
import com.bielfernandezb.samplebeerapp.presentation.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Navigator().navigateToBeerList(this)
    }
}