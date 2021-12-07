package com.bielfernandezb.samplebeerapp.model.repository.remote.api

import com.bielfernandezb.samplebeerapp.model.entities.Beer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersService {
    @GET("beers?")
    suspend fun getAllBears(
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): Response<List<Beer>>
}