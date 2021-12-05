package com.bielfernandezb.samplebeerapp.domain.repository

import androidx.lifecycle.LiveData
import com.bielfernandezb.samplebeerapp.core.Resource
import com.bielfernandezb.samplebeerapp.domain.entity.Beer
import kotlinx.coroutines.Job

interface BeerRepository {

    fun getBeers(page: Int, items: Int): LiveData<Resource<List<Beer>>>

    fun searchDatabase(searchQuery: String): LiveData<Resource<List<Beer>>>

    fun getBeer(id: Long): LiveData<Resource<Beer>>

    fun saveBeers(beers: List<Beer>): Job

    fun getAllBeers(): LiveData<Resource<List<Beer>>>
}