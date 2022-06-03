package com.bielfernandezb.samplebeerapp.beer_list.views.activities

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bielfernandezb.samplebeerapp.BaseActivity
import com.bielfernandezb.samplebeerapp.R
import com.bielfernandezb.samplebeerapp.beer_list.views.fragments.BeerListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val beerListFragment = BeerListFragment()
        fragmentTransaction.add(R.id.main_content, beerListFragment).commit()
    }
}