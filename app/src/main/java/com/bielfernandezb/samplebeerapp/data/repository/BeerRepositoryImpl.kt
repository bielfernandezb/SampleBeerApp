package com.bielfernandezb.samplebeerapp.data.repository

import com.bielfernandezb.samplebeerapp.data.repository.local.LocalDataSource
import com.bielfernandezb.samplebeerapp.data.repository.remote.RemoteDataSource
import com.bielfernandezb.samplebeerapp.data.utils.performDatabaseGetOperation
import com.bielfernandezb.samplebeerapp.data.utils.performGetOperation
import com.bielfernandezb.samplebeerapp.domain.repository.BeerRepository
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : BeerRepository {

    override fun getBeers(page: Int, items: Int) = performGetOperation(
        databaseQuery = { localDataSource.getBeers() },
        networkCall = { remoteDataSource.getBeers(page, items) },
        saveCallResult = { localDataSource.insertAll(it) }
    )

    override fun searchDatabase(searchQuery: String) = performDatabaseGetOperation(
        databaseQuery = { localDataSource.searchBeers(searchQuery) }
    )

    override fun getBeer(id: Long) = performDatabaseGetOperation(
        databaseQuery = { localDataSource.getBeer(id) }
    )

    override fun getAllBeers() = performDatabaseGetOperation(
        databaseQuery = { localDataSource.getBeers() }
    )

}