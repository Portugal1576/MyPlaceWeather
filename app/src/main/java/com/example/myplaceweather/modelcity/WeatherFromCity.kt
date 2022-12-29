package com.example.myplaceweather.modelcity

import com.example.myplaceweather.model.Clouds
import com.example.myplaceweather.model.Coord
import com.example.myplaceweather.model.Weather

data class WeatherFromCity(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)