package com.bielfernandezb.samplebeerapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.bielfernandezb.samplebeerapp.model.entities.Beer
import com.bielfernandezb.samplebeerapp.model.repository.Repository
import com.bielfernandezb.samplebeerapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeersDetailsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _id = MutableLiveData<Long>()

    private val _beer = _id.switchMap { id ->
        repository.getBeer(id)
    }

    val beer: LiveData<Resource<Beer>> = _beer

    fun start(id: Long) {
        _id.value = id
    }

}