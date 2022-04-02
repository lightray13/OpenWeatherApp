package com.testing.openweatherapp.data.local.repository.cityList

import androidx.lifecycle.LiveData
import com.testing.openweatherapp.data.local.database.WeatherDatabase
import com.testing.openweatherapp.data.local.database.model.City
import javax.inject.Inject

class CityListLocalDataSource @Inject constructor(private val database: WeatherDatabase) {
    val cities: LiveData<List<City>> = database.cityDao().cityList()

    suspend fun insertCitiesIntoDatabase(cities: List<City>) {
        if (cities.isNotEmpty()) {
            database.cityDao().insertCityList(cities)
        }
    }
}