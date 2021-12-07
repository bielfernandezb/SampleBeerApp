package com.bielfernandezb.samplebeerapp.model.repository.remote

import com.bielfernandezb.samplebeerapp.model.repository.remote.api.BeersService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val beerService: BeersService
) : BaseDataSource() {

    suspend fun getBeers(page: Int, items: Int) = getResult { beerService.getAllBears(page, items) }
}