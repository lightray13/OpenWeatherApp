package com.testing.openweatherapp.data.local.repository.cityList

import androidx.lifecycle.LiveData
import com.testing.openweatherapp.data.local.database.model.City
import com.testing.openweatherapp.data.local.preferences.SharedPreferenceStorage
import javax.inject.Inject

class CityListRepository @Inject constructor(
    private val cityListLocalDataSource: CityListLocalDataSource,
    private val preferenceStorage: SharedPreferenceStorage) {

    val cities: LiveData<List<City>> = cityListLocalDataSource.cities

    suspend fun insertCityList(list: List<City>) {
        if (isFirstLogin()) {
            cityListLocalDataSource.insertCitiesIntoDatabase(list)
            preferenceStorage.isFirstLoginAt = false
        }
    }

    private fun isFirstLogin(): Boolean {
        return preferenceStorage.isFirstLoginAt
    }
}