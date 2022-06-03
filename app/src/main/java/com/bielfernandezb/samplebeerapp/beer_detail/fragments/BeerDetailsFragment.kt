package com.bielfernandezb.samplebeerapp.beer_detail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bielfernandezb.samplebeerapp.databinding.FragmentBeerDetailBinding
import com.bielfernandezb.samplebeerapp.utils.Resource
import com.bielfernandezb.samplebeerapp.beer_detail.BeersDetailsViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailsFragment : Fragment() {

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
        arguments?.getLong("beerId")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.beer.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data != null) {
                        Glide.with(binding.root)
                            .load(it.data.imageURL)
                            .into(binding.detailsBearImage)
                        binding.descriptionTxt.text = it.data.brewersTips
                        binding.ipaTxt.text = it.data.name
                        binding.infoTxt.text = it.data.description
                        binding.yearTxt.text = it.data.firstBrewed
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
    }
}