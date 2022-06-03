package com.bielfernandezb.samplebeerapp.beer_detail.views.activities

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bielfernandezb.samplebeerapp.BaseActivity
import com.bielfernandezb.samplebeerapp.R
import com.bielfernandezb.samplebeerapp.beer_detail.views.fragments.BeerDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_details)

        val b = intent.extras
        if (b != null) {
            val value = b.getLong("beerId")
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            val beerListFragment = BeerDetailsFragment()
            val bundle = Bundle()
            bundle.putLong("beerId", value)
            beerListFragment.arguments = bundle
            fragmentTransaction.add(R.id.main_content, beerListFragment).commit()
        }
    }
}