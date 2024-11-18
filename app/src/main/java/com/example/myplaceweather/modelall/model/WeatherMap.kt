package com.example.myplaceweather.modelall.model

data class WeatherMap(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherList>,
    val message: Int
)