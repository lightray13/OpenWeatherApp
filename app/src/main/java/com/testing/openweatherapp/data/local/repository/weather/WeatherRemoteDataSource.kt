package com.testing.openweatherapp.data.local.repository.weather

import com.testing.openweatherapp.api.ApiInterface
import com.testing.openweatherapp.api.BaseRemoteDataSource
import com.testing.openweatherapp.api.model.WeatherMap
import com.testing.openweatherapp.api.Result
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(private val service: ApiInterface): BaseRemoteDataSource() {

    suspend fun weatherByCity(appid: String, units: String, q: String): Result<WeatherMap> =
        getResult {
            service.getWeatherByCityName(appid, units, q)
        }
}