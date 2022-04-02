package com.testing.openweatherapp.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testing.openweatherapp.data.local.database.model.City

@Dao
interface CityDao {

    @Query("SELECT * FROM city_list ORDER BY LOWER(name) ASC")
    fun cityList(): LiveData<List<City>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityList(list: List<City>)
}