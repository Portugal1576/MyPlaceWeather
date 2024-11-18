package com.example.myplaceweather.data.apimoon

import com.example.myplaceweather.modelall.modelmoon.MoonResponse
import com.example.myplaceweather.utils.KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMoon {
    @GET("/data/3.0/onecall")
    suspend fun getMoonResponse(
        @Query("lat") lat: Double = 0.0,
        @Query("lon") lon: Double = 0.0,
        @Query("exclude") exclude: String = "current",
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ua",
        @Query("appid") appid: String = KEY
    ): Response<MoonResponse>
}