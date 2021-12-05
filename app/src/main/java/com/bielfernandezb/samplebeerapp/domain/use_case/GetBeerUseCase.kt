package com.bielfernandezb.samplebeerapp.domain.use_case

import androidx.lifecycle.LiveData
import com.bielfernandezb.samplebeerapp.core.Resource
import com.bielfernandezb.samplebeerapp.domain.entity.Beer
import com.bielfernandezb.samplebeerapp.domain.repository.BeerRepository
import javax.inject.Inject

class GetBeerUseCase @Inject constructor(
    private val beerRepository: BeerRepository
) : BaseUseCase<Long, LiveData<Resource<Beer>>> {

    override fun invoke(params: Long): LiveData<Resource<Beer>> =
        beerRepository.getBeer(params)

}