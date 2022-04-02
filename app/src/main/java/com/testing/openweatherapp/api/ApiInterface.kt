package com.testing.openweatherapp.api

import com.testing.openweatherapp.api.model.WeatherMap
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/data/2.5/weather")
    suspend fun getWeatherByCityName(
        @Query("appid") appid: String,
        @Query("units") units: String,
        @Query("q") q: String
    ): Response<WeatherMap>
}