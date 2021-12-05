package com.bielfernandezb.samplebeerapp.presentation.beer_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.bielfernandezb.samplebeerapp.core.Resource
import com.bielfernandezb.samplebeerapp.domain.entity.Beer
import com.bielfernandezb.samplebeerapp.domain.use_case.GetRemoteBeerListUseCase
import com.bielfernandezb.samplebeerapp.domain.use_case.SearchBeerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeersListViewModel @Inject constructor(
    private val beerListUseCase: GetRemoteBeerListUseCase,
    private val searchBeerBaseUseCase: SearchBeerUseCase
) : ViewModel() {

    private val _page = MutableLiveData<Int>()
    private val _query = MutableLiveData<String>()

    private var _beers = _page.switchMap { page ->
        beerListUseCase(Pair(page, 10))
    }

    val beers: LiveData<Resource<List<Beer>>> = _beers

    private var _filterBeers = _query.switchMap { query ->
        searchDatabase(query)
    }

    val filterBeers: LiveData<Resource<List<Beer>>> = _filterBeers

    fun start(page: Int) {
        _page.value = page
    }

    fun setQuery(query: String) {
        _query.value = query
    }

    private fun searchDatabase(searchQuery: String): LiveData<Resource<List<Beer>>> {
        return searchBeerBaseUseCase(searchQuery)
    }

}
