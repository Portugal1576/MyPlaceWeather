package com.example.myplaceweather.repository

import com.example.myplaceweather.data.api.RetrofitInstance
import com.example.myplaceweather.data.apicity.RetrofitCityInstance
import com.example.myplaceweather.model.WeatherMap
import com.example.myplaceweather.modelcity.WeatherFromCity
import com.example.myplaceweather.utils.KEY
import retrofit2.Response

class Repository {
    suspend fun getWeather(lat: Double, lon: Double): Response<WeatherMap> {
        return RetrofitInstance.api.getWeather(lat, lon, KEY)
    }

    suspend fun getCity(city: String): Response<WeatherFromCity> {
        return RetrofitCityInstance.apiCity.getCityWeather(city, KEY, "metric")
    }
}