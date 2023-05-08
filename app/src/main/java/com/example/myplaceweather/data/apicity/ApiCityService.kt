package com.example.myplaceweather.data.apicity

import com.example.myplaceweather.modelall.modelcity.WeatherFromCity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCityService {
    @GET("/data/2.5/weather")
    suspend fun getCityWeather(
        @Query("q") q: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): Response<WeatherFromCity>
}