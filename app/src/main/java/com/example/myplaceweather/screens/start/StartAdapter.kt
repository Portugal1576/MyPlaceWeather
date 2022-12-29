package com.example.myplaceweather.screens.start

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myplaceweather.R
import com.example.myplaceweather.model.WeatherMap
import com.example.myplaceweather.utils.image_url
import kotlinx.android.synthetic.main.item_list_weather.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class StartAdapter : RecyclerView.Adapter<StartAdapter.StartViewHolder>() {
    private var listMap = emptyList<WeatherMap>()
    private var count = 0


    class StartViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_weather, parent, false)
        return StartViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StartViewHolder, position: Int) {

        val dayString = listMap[0].list[position].dt_txt
        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(dayString, pattern)
        val day = localDateTime.dayOfWeek
        val time = localDateTime.toLocalTime()


        holder.itemView.tv_data.text = "$day  $time"
        holder.itemView.tv_temperature.text =
            ((listMap[0].list[position].main.temp) - 273.15).toInt().toString() + " °C"
        holder.itemView.tv_humidity.text = listMap[0].list[position].main.humidity.toString() + " %"
        holder.itemView.tv_pressure.text =
            ((listMap[0].list[position].main.pressure) * 0.750062).toInt().toString() + " mm Hg"
        holder.itemView.tv_wind.text = listMap[0].list[position].wind.speed.toString() + " m/sec"
        val image = listMap[0].list[position].weather[0].icon
        val url = image_url + "${image}.png"
        Glide.with(holder.itemView.context)
            .load(url)
            .placeholder(R.drawable.ic_baseline_place_24)
            .error(R.drawable.ic_baseline_place_24)
            .into(holder.itemView.image_icon)
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