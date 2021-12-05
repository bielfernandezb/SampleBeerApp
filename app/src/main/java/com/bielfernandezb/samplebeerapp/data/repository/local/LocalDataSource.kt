package com.bielfernandezb.samplebeerapp.data.repository.local

import com.bielfernandezb.samplebeerapp.data.repository.local.db.BeerDao
import com.bielfernandezb.samplebeerapp.domain.entity.Beer
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val beerDao: BeerDao
) {

    fun getBeers() = beerDao.getAllBeers()

    fun searchBeers(searchQuery: String) = beerDao.searchBeer(searchQuery)

    fun getBeer(id: Long) = beerDao.getBeer(id)

    suspend fun insertAll(beers: List<Beer>) = beerDao.insertAll(beers)
}