package com.testing.openweatherapp.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testing.openweatherapp.data.local.database.model.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_list WHERE name = :name")
    suspend fun weatherByCity(name: String): WeatherEntity?

    @Query("SELECT * FROM weather_list WHERE name = :name")
    fun weatherLiveDataByCity(name: String): LiveData<WeatherEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(data: WeatherEntity)
}