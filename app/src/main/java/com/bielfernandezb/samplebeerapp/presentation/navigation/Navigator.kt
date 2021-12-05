package com.bielfernandezb.samplebeerapp.presentation.navigation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bielfernandezb.samplebeerapp.R
import com.bielfernandezb.samplebeerapp.presentation.beer_detail.view.fragment.BeerDetailsFragment
import com.bielfernandezb.samplebeerapp.presentation.beer_list.views.activity.MainActivity
import com.bielfernandezb.samplebeerapp.presentation.beer_list.views.fragment.BeerListFragment

class Navigator {

    companion object {
        const val BEER_ID = "beerId"
    }

    fun navigateToBeerList(activity: AppCompatActivity) {
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val beerListFragment = BeerListFragment()
        fragmentTransaction.replace(R.id.mainContent, beerListFragment).commit()
    }

    fun navigateToBeerDetails(fragment: Fragment, beerId: Long) {
        val fragmentManager: FragmentManager = fragment.requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val beerListFragment = BeerDetailsFragment()
        val bundle = Bundle()
        bundle.putLong(BEER_ID, beerId)
        beerListFragment.arguments = bundle
        fragmentTransaction.replace(R.id.mainContent, beerListFragment)
            .addToBackStack(fragment::class.java.toString()).commit()
    }

    fun navigateToMainActivity(currentActivity: AppCompatActivity) {
        val intent = Intent(currentActivity, MainActivity::class.java)
        currentActivity.startActivity(intent)
        currentActivity.finish()
    }
}