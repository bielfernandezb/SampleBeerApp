package com.bielfernandezb.samplebeerapp.data.repository.remote.api

import com.bielfernandezb.samplebeerapp.data.repository.remote.dto.BeerDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersService {
    @GET("beers?")
    suspend fun getAllBears(
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): Response<List<BeerDto>>
}