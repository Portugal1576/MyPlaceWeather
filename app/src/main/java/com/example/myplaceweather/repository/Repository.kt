package com.example.myplaceweather.repository

import com.example.myplaceweather.R
import com.example.myplaceweather.data.api.RetrofitInstance
import com.example.myplaceweather.data.apicity.RetrofitCityInstance
import com.example.myplaceweather.data.apimoon.RetrofitMoonInstance
import com.example.myplaceweather.modelall.model.WeatherMap
import com.example.myplaceweather.modelall.modelcity.WeatherFromCity
import com.example.myplaceweather.modelall.modelmoon.MoonResponse
import com.example.myplaceweather.utils.KEY
import retrofit2.Response

class Repository {
    suspend fun getWeather(lat: Double, lon: Double): Response<WeatherMap> {
        return RetrofitInstance.api.getWeather(lat, lon, KEY)
    }

    suspend fun getMoon(lat: Double, lon: Double): Response<MoonResponse> {
        return RetrofitMoonInstance.apiMoon.getMoonResponse(lat, lon)
    }

    suspend fun getCity(city: String): Response<WeatherFromCity> {
        return RetrofitCityInstance.apiCity.getCityWeather(city, KEY, "metric")
    }

    fun getMoonImage(fase: Double): Int {

        when (fase) {
            0.0 -> return R.drawable.m_1
            in 0.0001..0.03 -> return R.drawable.m_1_1
            in 0.031..0.07 -> return R.drawable.m_1_2
            in 0.071..0.11 -> return R.drawable.m_1_3
            in 0.111..0.15 -> return R.drawable.m_1_4
            in 0.151..0.19 -> return R.drawable.m_1_5
            in 0.191..0.22 -> return R.drawable.m_1_6
            in 0.221..0.2499 -> return R.drawable.m_1_7
            0.25 -> return R.drawable.m_0_25
            in 0.2499..0.29 -> return R.drawable.m_0_25_1
            in 0.291..0.33 -> return R.drawable.m_0_25_2
            in 0.331..0.37 -> return R.drawable.m_0_25_3
            in 0.371..0.41 -> return R.drawable.m_0_25_4
            in 0.411..0.45 -> return R.drawable.m_0_25_5
            in 0.451..0.4999 -> return R.drawable.m_0_25_6
            0.5 -> return R.drawable.m_0_5
            in 0.5001..0.53 -> return R.drawable.m_0_5_1
            in 0.531..0.57 -> return R.drawable.m_0_5_2
            in 0.571..0.61 -> return R.drawable.m_0_5_3
            in 0.611..0.65 -> return R.drawable.m_0_5_4
            in 0.651..0.69 -> return R.drawable.m_0_5_5
            in 0.691..0.72 -> return R.drawable.m_0_5_6
            in 0.721..0.7499 -> return R.drawable.m_0_5_7
            0.75 -> return R.drawable.m_0_75
            in 0.7499..0.79 -> return R.drawable.m_0_75_1
            in 0.791..0.83 -> return R.drawable.m_0_75_2
            in 0.831..0.87 -> return R.drawable.m_0_75_3
            in 0.871..0.91 -> return R.drawable.m_0_75_4
            in 0.911..0.95 -> return R.drawable.m_0_75_5
            in 0.951..0.9999 -> return R.drawable.m_0_75_6
            1.0 -> return R.drawable.m_1
        }
        return R.drawable.error
    }
}