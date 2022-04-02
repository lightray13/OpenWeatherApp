package com.testing.openweatherapp.data.local.repository.weather

import androidx.lifecycle.LiveData
import com.testing.openweatherapp.util.Constants
import javax.inject.Inject
import com.testing.openweatherapp.api.Result
import com.testing.openweatherapp.api.successed
import com.testing.openweatherapp.data.local.database.model.WeatherEntity

class WeatherRepository @Inject constructor(
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) {

    suspend fun loadWeatherByCity(name: String) {
        when(val result = weatherRemoteDataSource.weatherByCity(Constants.APP_ID,
            Constants.APP_UNITS, name)) {
            is Result.Success -> {
                if (result.successed) {
                    val resultWeather = result.data
                    resultWeather.let {
                        val weatherEntity = WeatherEntity(
                            it.id ?: 0,
                            it.name,
                            it.weather,
                            it.main?.temp,
                            it.main?.feelsLike,
                            it.main?.humidity,
                            it.main?.seaLevel,
                            it.main?.grndLevel,
                            it.wind?.speed,
                            it.wind?.deg
                        )
                        weatherLocalDataSource.addWeatherIntoDatabase(weatherEntity)
                    }
                    Result.Success(true)
            } else {
                Result.Error(Constants.GENERIC_ERROR)
            }
            }
            else -> result as Result.Error
        }
    }

    fun weatherByCity(name: String): LiveData<WeatherEntity?> = weatherLocalDataSource.weatherByCity(name)
}