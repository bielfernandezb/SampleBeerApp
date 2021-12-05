package com.bielfernandezb.samplebeerapp.data.repository.remote

import com.bielfernandezb.samplebeerapp.data.repository.remote.api.BeersService
import com.bielfernandezb.samplebeerapp.data.repository.remote.mapper.BeerModelDataMapper
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val beerService: BeersService
) : BaseDataSource() {

    suspend fun getBeers(page: Int, items: Int) =
        BeerModelDataMapper().transform(getResult { beerService.getAllBears(page, items) })
}