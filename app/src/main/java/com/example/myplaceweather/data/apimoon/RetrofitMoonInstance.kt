package com.example.myplaceweather.data.apimoon

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitMoonInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiMoon: ApiMoon by lazy {
        retrofit.create(ApiMoon::class.java)
    }
}