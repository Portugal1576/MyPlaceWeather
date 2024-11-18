package com.example.myplaceweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplaceweather.modelall.model.Coord
import com.example.myplaceweather.modelall.model.WeatherMap
import com.example.myplaceweather.modelall.modelcity.WeatherFromCity
import com.example.myplaceweather.modelall.modelmoon.MoonResponse
import com.example.myplaceweather.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    val coordinates: MutableLiveData<Coord> by lazy {
        MutableLiveData<Coord>()
    }

    private val repo = Repository()

    val myWeatherList: MutableLiveData<Response<WeatherMap>> = MutableLiveData()
    val myMoonPhaseList: MutableLiveData<Response<MoonResponse>> = MutableLiveData()
    val myCityWeather: MutableLiveData<Response<WeatherFromCity>> = MutableLiveData()
    private var moonPhase: Int = 2131165473

    fun getWeatherList() {
        viewModelScope.launch {
            myWeatherList.value = repo.getWeather(coordinates.value!!.lat, coordinates.value!!.lon)
        }
    }

    fun getImageMoonPhase(phase: Double): Int {
        viewModelScope.launch {
            moonPhase = repo.getMoonImage(phase)
        }
        return moonPhase
    }

    fun getMoonPhase() {
        viewModelScope.launch {
            myMoonPhaseList.value = repo.getMoon(coordinates.value!!.lat, coordinates.value!!.lon)
        }
    }

    fun getCityCoordinates(city: String) {
        viewModelScope.launch {
            myCityWeather.value = repo.getCity(city)
        }
    }
}