package com.bielfernandezb.samplebeerapp.data.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.bielfernandezb.samplebeerapp.core.Resource
import kotlinx.coroutines.*

fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }

fun <A> performRemoteGetOperation(
    networkCall: suspend () -> Resource<A>
): LiveData<Resource<A>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            val source = responseStatus.data!!
            emit(Resource.success(source))
        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
        }
    }

fun <T> performDatabaseGetOperation(
    databaseQuery: () -> LiveData<T>
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)
    }

@OptIn(DelicateCoroutinesApi::class)
fun performDatabaseInsertOperation(
    saveCallResult: suspend () -> Unit
): Job =
    GlobalScope.launch {
        saveCallResult.invoke()
    }

