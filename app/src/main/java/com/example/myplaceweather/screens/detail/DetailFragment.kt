package com.example.myplaceweather.screens.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.myplaceweather.MainViewModel
import com.example.myplaceweather.R
import com.example.myplaceweather.databinding.FragmentDetailBinding
import com.example.myplaceweather.utils.APP
import com.example.myplaceweather.utils.image_url
import kotlinx.android.synthetic.main.item_list_weather.view.*

class DetailFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getWeatherList()
        mainViewModel.myWeatherList.observe(viewLifecycleOwner) { list ->
            list.body()?.let {
                binding.tvCity.text = it.city.name
                binding.tvData.text = it.list[0].dt_txt
                binding.tvDescription.text = it.list[0].weather[0].description
                binding.tvTemperature.text = ((it.list[0].main.temp) - 273.15).toInt().toString()+" °C"
                binding.tvHumidity.text = it.list[0].main.humidity.toString() + " %"
                binding.tvPressure.text =
                    ((it.list[0].main.pressure) * 0.750062).toInt().toString() + " mm Hg"
                binding.tvWind.text = it.list[0].wind.speed.toString() + " m/sec"
                binding.tvVisibility.text = it.list[0].visibility.toString() + " m"
                binding.tvSeaLevel.text = ((it.list[0].main.sea_level) * 0.750062).toInt().toString() + " mm Hg"
                binding.tvTempMin.text = ((it.list[0].main.temp_min) - 273.15).toInt().toString()+" °C"
                binding.tvTempMax.text = ((it.list[0].main.temp_max) - 273.15).toInt().toString()+" °C"

                val image = it.list[0].weather[0].icon
                val url = image_url + "${image}.png"
                Glide.with(binding.root)
                    .load(url)
                    .placeholder(R.drawable.ic_baseline_place_24)
                    .error(R.drawable.ic_baseline_place_24)
                    .into(binding.imageIcon)
            }
        }

        binding.btn5Days.setOnClickListener{
            APP.navController.navigate(
                R.id.action_detailFragment_to_startFragment)
        }

        binding.btnChoose.setOnClickListener {
            APP.navController.navigate(
                R.id.action_detailFragment_to_choiceFragment)
        }
    }
}