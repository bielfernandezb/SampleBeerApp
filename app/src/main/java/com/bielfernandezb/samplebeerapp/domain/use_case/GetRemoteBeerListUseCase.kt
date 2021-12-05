package com.bielfernandezb.samplebeerapp.domain.use_case

import androidx.lifecycle.LiveData
import com.bielfernandezb.samplebeerapp.core.Resource
import com.bielfernandezb.samplebeerapp.domain.entity.Beer
import com.bielfernandezb.samplebeerapp.domain.repository.BeerRepository
import javax.inject.Inject

class GetRemoteBeerListUseCase @Inject constructor(
    private val beerRepository: BeerRepository
) : BaseUseCase<Pair<Int, Int>, LiveData<Resource<List<Beer>>>> {

    override fun invoke(params: Pair<Int, Int>): LiveData<Resource<List<Beer>>> =
        beerRepository.getBeers(params.first, params.second)

}