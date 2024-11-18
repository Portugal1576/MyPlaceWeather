package com.example.myplaceweather.utils

import com.example.myplaceweather.MainActivity
import com.example.myplaceweather.R

//URL_PLACE = "https://api.openweathermap.org/data/2.5/weather?lat=41.13&lon=-7.79&appid={KEY}"
//URL_PLACE_5_DAYS = "https://api.openweathermap.org/data/2.5/forecast?lat=44.34&lon=10.99&appid={KEY}"
//coordSande = Coord(41.1296529, -7.7850178)
//https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric


//One Call API 3.0
//https://api.openweathermap.org/data/3.0/onecall?lat=41.1296529&lon=-7.7850178&exclude=current&units=metric&lang=ua&appid=https://api.openweathermap.org/data/3.0/onecall?lat=41.1296529&lon=-7.7850178&exclude=current&units=metric&lang=ua&appid=f95da1f1cc9695f7f3c23deb1d56e3ce

const val KEY = "f95da1f1cc9695f7f3c23deb1d56e3ce"
const val image_url = "https://openweathermap.org/img/w/"
lateinit var APP: MainActivity
const val SPRING = R.drawable.spring_portrait
const val SPRING_LAND = R.drawable.spring_landscape
const val SUMMER = R.drawable.summer_portrait
const val SUMMER_LAND = R.drawable.summer_landscape
const val AUTUMN = R.drawable.autumn_portrait
const val AUTUMN_LAND = R.drawable.autumn_landscape
const val WINTER = R.drawable.winter_portrait
const val WINTER_LAND = R.drawable.winter_landscape

