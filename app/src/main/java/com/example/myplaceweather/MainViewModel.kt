package com.example.myplaceweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplaceweather.model.Coord
import com.example.myplaceweather.model.WeatherMap
import com.example.myplaceweather.modelcity.WeatherFromCity
import com.example.myplaceweather.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    val coordinates: MutableLiveData<Coord> by lazy {
        MutableLiveData<Coord>()
    }

    val city: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val repo = Repository()

    val myWeatherList: MutableLiveData<Response<WeatherMap>> = MutableLiveData()
    val myCityWeather: MutableLiveData<Response<WeatherFromCity>> = MutableLiveData()

    fun getWeatherList() {
        viewModelScope.launch {
            myWeatherList.value = repo.getWeather(coordinates.value!!.lat, coordinates.value!!.lon)
        }
    }

    fun getCityCoordinats(city: String) {
        viewModelScope.launch {
            myCityWeather.value = repo.getCity(city)
        }
    }
}