package com.bielfernandezb.samplebeerapp.domain.use_case

import com.bielfernandezb.samplebeerapp.domain.entity.Beer
import com.bielfernandezb.samplebeerapp.domain.repository.BeerRepository
import kotlinx.coroutines.Job
import javax.inject.Inject

class SaveBeerListUseCase @Inject constructor(
    private val beerRepository: BeerRepository
) : BaseUseCase<List<Beer>, Job> {

    override fun invoke(beers: List<Beer>): Job =
        beerRepository.saveBeers(beers)

}