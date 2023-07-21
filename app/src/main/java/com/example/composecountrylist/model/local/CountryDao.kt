package com.example.composecountrylist.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalCountry(countryEntity: List<CountryEntity>)


    @Query("SELECT * FROM CountryEntity order by id")
    suspend fun getAllCachedCountries(): List<CountryEntity>

}