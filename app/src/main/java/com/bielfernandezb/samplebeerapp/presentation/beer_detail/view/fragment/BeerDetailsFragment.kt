package com.bielfernandezb.samplebeerapp.presentation.beer_detail.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bielfernandezb.samplebeerapp.core.Resource
import com.bielfernandezb.samplebeerapp.databinding.FragmentBeerDetailBinding
import com.bielfernandezb.samplebeerapp.presentation.BaseFragment
import com.bielfernandezb.samplebeerapp.presentation.beer_detail.viewmodel.BeersDetailsViewModel
import com.bielfernandezb.samplebeerapp.presentation.navigation.Navigator.Companion.BEER_ID
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentBeerDetailBinding
    private val viewModel: BeersDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBeerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getLong(BEER_ID)?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.beer.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { beer ->
                        Glide.with(binding.root)
                            .load(beer.imageURL)
                            .into(binding.imageBeerDetails)
                        binding.textBeerDescription.text = beer.brewersTips
                        binding.textBeerIpa.text = beer.name
                        binding.textBeerInformation.text = beer.description
                        binding.textBeerYear.text = beer.firstBrewed
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
    }
}