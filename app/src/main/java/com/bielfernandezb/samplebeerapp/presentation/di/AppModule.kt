package com.bielfernandezb.samplebeerapp.presentation.di

import com.bielfernandezb.samplebeerapp.data.repository.BeerRepositoryImpl
import com.bielfernandezb.samplebeerapp.domain.use_case.*
import com.bielfernandezb.samplebeerapp.presentation.beer_list.viewmodel.BeersListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGetBeerUseCase(repository: BeerRepositoryImpl): GetBeerUseCase =
        GetBeerUseCase(repository)

    @Singleton
    @Provides
    fun provideGetAllBeerListUseCase(repository: BeerRepositoryImpl): GetLocalBeerListUseCase =
        GetLocalBeerListUseCase(repository)

    @Singleton
    @Provides
    fun provideGetBeerListUseCase(repository: BeerRepositoryImpl): GetRemoteBeerListUseCase =
        GetRemoteBeerListUseCase(repository)


    @Singleton
    @Provides
    fun provideSearchBeerUseCase(repository: BeerRepositoryImpl): SearchBeerUseCase =
        SearchBeerUseCase(repository)

    @Singleton
    @Provides
    fun provideUseCasesBeersListViewModel(
        getBeerListUseCase: GetRemoteBeerListUseCase,
        searchBeerUseCase: SearchBeerUseCase
    ) = BeersListViewModel(
        getBeerListUseCase, searchBeerUseCase
    )
}