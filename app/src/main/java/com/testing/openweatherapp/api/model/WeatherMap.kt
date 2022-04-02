package com.testing.openweatherapp.api.model

import com.google.gson.annotations.SerializedName

data class WeatherMap (
        val id: Int?,
        val name: String?,
        val main: Main?,
        val wind: Wind?,
        val weather: List<Weather>?)

data class Main (
        val temp: Double?,
        @SerializedName("feels_like") val feelsLike: Double?,
        val humidity: Int?,
        @SerializedName("sea_level") val seaLevel: Int?,
        @SerializedName("grnd_level") val grndLevel: Int?,
         )

data class Wind (
        val speed: Double?,
        val deg: Int?)

data class Weather(
        val icon: String?,
        val description: String?
)