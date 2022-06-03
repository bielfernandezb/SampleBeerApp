package com.bielfernandezb.samplebeerapp.beer_list

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
class BeersListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _page = MutableLiveData<Int>()

    private var _beers = _page.switchMap { page ->
        repository.getBeers(page, 10)
    }
    val beers: LiveData<Resource<List<Beer>>> = _beers

    fun start(page: Int) {
        _page.value = page
    }

    fun searchDatabase(searchQuery: String): LiveData<Resource<List<Beer>>> {
        return repository.searchDatabase(searchQuery)
    }

}
