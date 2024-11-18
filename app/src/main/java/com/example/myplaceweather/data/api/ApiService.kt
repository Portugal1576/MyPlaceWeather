package com.example.myplaceweather.data.api

import com.example.myplaceweather.modelall.model.WeatherMap
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/data/2.5/forecast")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ): Response<WeatherMap>
}