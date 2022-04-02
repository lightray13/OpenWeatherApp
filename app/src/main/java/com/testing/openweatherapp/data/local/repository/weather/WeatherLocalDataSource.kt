package com.testing.openweatherapp.data.local.repository.weather

import androidx.lifecycle.LiveData
import com.testing.openweatherapp.data.local.database.WeatherDatabase
import com.testing.openweatherapp.data.local.database.model.WeatherEntity
import javax.inject.Inject

class WeatherLocalDataSource @Inject constructor(private val database: WeatherDatabase) {

    fun weatherByCity(city: String): LiveData<WeatherEntity?> = database.weatherDao().weatherLiveDataByCity(city)

    suspend fun addWeatherIntoDatabase(weather: WeatherEntity) {
        database.weatherDao().insertWeather(weather)
    }
}