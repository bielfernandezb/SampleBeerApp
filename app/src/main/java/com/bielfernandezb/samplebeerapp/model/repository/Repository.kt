package com.bielfernandezb.samplebeerapp.model.repository

import com.bielfernandezb.samplebeerapp.model.repository.local.LocalDataSource
import com.bielfernandezb.samplebeerapp.model.repository.local.utils.performGetOperation
import com.bielfernandezb.samplebeerapp.model.repository.local.utils.performSingleDatabaseGetOperation
import com.bielfernandezb.samplebeerapp.model.repository.remote.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    fun getBeers(page: Int, items: Int) = performGetOperation(
        databaseQuery = { localDataSource.getBeers() },
        networkCall = { remoteDataSource.getBeers(page, items) },
        saveCallResult = { localDataSource.insertAll(it) }
    )

    fun searchDatabase(searchQuery: String) = performSingleDatabaseGetOperation(
        databaseQuery = { localDataSource.searchBeers(searchQuery) }
    )

    fun getBeer(id: Long) = performSingleDatabaseGetOperation(
        databaseQuery = { localDataSource.getBeer(id) }
    )

}