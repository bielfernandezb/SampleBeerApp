package com.bielfernandezb.samplebeerapp.model.repository.local

import com.bielfernandezb.samplebeerapp.model.entities.Beer
import com.bielfernandezb.samplebeerapp.model.repository.local.db.BeerDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val beerDao: BeerDao
) {

    fun getBeers() = beerDao.getAllBeers()

    fun searchBeers(searchQuery: String) = beerDao.searchBeer(searchQuery)

    fun getBeer(id: Long) = beerDao.getBeer(id)

    suspend fun insertAll(beers: List<Beer>) = beerDao.insertAll(beers)
}