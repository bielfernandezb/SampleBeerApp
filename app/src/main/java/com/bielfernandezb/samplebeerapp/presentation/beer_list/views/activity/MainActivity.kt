package com.bielfernandezb.samplebeerapp.presentation.beer_list.views.activity

import android.os.Bundle
import com.bielfernandezb.samplebeerapp.databinding.ActivityMainBinding
import com.bielfernandezb.samplebeerapp.presentation.BaseActivity
import com.bielfernandezb.samplebeerapp.presentation.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Navigator().navigateToBeerList(this)
    }

}