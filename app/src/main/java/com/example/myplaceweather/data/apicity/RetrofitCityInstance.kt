package com.example.myplaceweather.data.apicity

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCityInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiCity: ApiCityService by lazy {
        retrofit.create(ApiCityService::class.java)
    }
}