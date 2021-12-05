package com.bielfernandezb.samplebeerapp.domain.use_case

import androidx.lifecycle.LiveData
import com.bielfernandezb.samplebeerapp.core.Resource
import com.bielfernandezb.samplebeerapp.domain.entity.Beer
import com.bielfernandezb.samplebeerapp.domain.repository.BeerRepository
import javax.inject.Inject

class SearchBeerUseCase @Inject constructor(
    private val beerRepository: BeerRepository
) : BaseUseCase<String, LiveData<Resource<List<Beer>>>> {

    override fun invoke(query: String): LiveData<Resource<List<Beer>>> =
        beerRepository.searchDatabase(query)

}