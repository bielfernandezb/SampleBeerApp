package com.bielfernandezb.samplebeerapp.data.repository.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bielfernandezb.samplebeerapp.domain.entity.Beer

@Dao
interface BeerDao {

    @Query("SELECT * FROM beers")
    fun getAllBeers(): LiveData<List<Beer>>

    @Query("SELECT * FROM beers WHERE id = :id")
    fun getBeer(id: Long): LiveData<Beer>

    @Query("SELECT * FROM beers WHERE name LIKE :name || '%'")
    fun searchBeer(name: String): LiveData<List<Beer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(beers: List<Beer>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(beer: Beer)
}