package com.bielfernandezb.samplebeerapp.data.di

import android.content.Context
import com.bielfernandezb.samplebeerapp.data.repository.BeerRepositoryImpl
import com.bielfernandezb.samplebeerapp.data.repository.local.LocalDataSource
import com.bielfernandezb.samplebeerapp.data.repository.local.db.AppDatabase
import com.bielfernandezb.samplebeerapp.data.repository.remote.RemoteDataSource
import com.bielfernandezb.samplebeerapp.data.repository.remote.api.ApiClient
import com.bielfernandezb.samplebeerapp.data.repository.remote.api.ApiClient.okHttpClient
import com.bielfernandezb.samplebeerapp.data.repository.remote.api.BeersService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(ApiClient.BASE_URL)
        .addConverterFactory(ApiClient.gSONConverter)
        .client(okHttpClient)
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideBeerService(retrofit: Retrofit): BeersService =
        retrofit.create(BeersService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideBeerDao(db: AppDatabase) = db.beerDao()

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = BeerRepositoryImpl(localDataSource, remoteDataSource)
}