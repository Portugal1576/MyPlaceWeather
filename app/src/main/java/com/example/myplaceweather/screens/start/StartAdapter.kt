package com.example.myplaceweather.screens.start

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myplaceweather.R
import com.example.myplaceweather.databinding.ItemListWeatherBinding
import com.example.myplaceweather.modelall.model.WeatherMap
import com.example.myplaceweather.utils.image_url
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class StartAdapter : RecyclerView.Adapter<StartAdapter.StartViewHolder>() {
    private var listMap = emptyList<WeatherMap>()
    private var count = 0

    class StartViewHolder(val binding: ItemListWeatherBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartViewHolder {
        val binding = ItemListWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StartViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StartViewHolder, position: Int) {
        val binding = holder.binding
        val dayString = listMap[0].list[position].dt_txt
        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(dayString, pattern)
        val day = localDateTime.dayOfWeek
        val time = localDateTime.toLocalTime()

        binding.tvData.text = "$day  $time"
        binding.tvTemperature.text =
            ((listMap[0].list[position].main.temp) - 273.15).toInt().toString() + " °C"
        binding.tvHumidity.text = listMap[0].list[position].main.humidity.toString() + " %"
        binding.tvPressure.text =
            ((listMap[0].list[position].main.pressure) * 0.750062).toInt().toString() + " mm Hg"
        binding.tvWind.text = listMap[0].list[position].wind.speed.toString() + " m/sec"
        binding.imageWindItem.rotation = listMap[0].list[position].wind.deg.toFloat()
        binding.tvWindGust.text = listMap[0].list[position].wind.gust.toString() + " m/sec"

        val image = listMap[0].list[position].weather[0].icon
        val url = image_url + "${image}.png"
        Glide.with(binding.root.context)
            .load(url)
            .placeholder(R.drawable.ic_baseline_place_24)
            .error(R.drawable.ic_baseline_place_24)
            .into(binding.imageIcon)
    }

    override fun getItemCount(): Int {
        return count
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: WeatherMap) {
        listMap = listOf(list)
        count = listMap[0].list.size
        notifyDataSetChanged()
    }
}
