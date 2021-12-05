package com.bielfernandezb.samplebeerapp.presentation.beer_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.bielfernandezb.samplebeerapp.core.Resource
import com.bielfernandezb.samplebeerapp.domain.entity.Beer
import com.bielfernandezb.samplebeerapp.domain.use_case.GetBeerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeersDetailsViewModel @Inject constructor(
    private val getBeerUseCase: GetBeerUseCase
) : ViewModel() {

    private val _id = MutableLiveData<Long>()

    private val _beer = _id.switchMap { id ->
        getBeerUseCase(id)
    }

    val beer: LiveData<Resource<Beer>> = _beer

    fun start(id: Long) {
        _id.value = id
    }

}