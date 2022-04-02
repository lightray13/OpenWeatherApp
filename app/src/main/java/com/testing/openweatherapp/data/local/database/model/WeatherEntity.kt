package com.testing.openweatherapp.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testing.openweatherapp.api.model.Weather

@Entity(tableName = "weather_list")
data class WeatherEntity (
    @PrimaryKey val id: Int,
    val name: String?,
    val weather: List<Weather>?,
    val temp: Double?,
    val feelsLike: Double?,
    val humidity: Int?,
    val seaLevel: Int?,
    val grndLevel: Int?,
    val windSpeed: Double?,
    val windDeg: Int?,
)