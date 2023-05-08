package com.example.myplaceweather.screens.start

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaceweather.MainViewModel
import com.example.myplaceweather.R
import com.example.myplaceweather.databinding.FragmentStartBinding
import com.example.myplaceweather.utils.APP
import kotlin.math.roundToInt

@Suppress("UNREACHABLE_CODE")
class StartFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StartAdapter
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater)
        mainViewModel.coordinates.observe(activity as LifecycleOwner) {}

        recyclerView = binding.rvWeather
        adapter = StartAdapter()
        recyclerView.adapter = adapter


        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.coordinates.observe(activity as LifecycleOwner) {
            binding.tvLat.text = "Lat: " + it.lat.toCoordinats()
            binding.tvLon.text = "Lon: " + it.lon.toCoordinats()
        }
        mainViewModel.getWeatherList()

//        mainViewModel.getMoonFase()

        mainViewModel.myWeatherList.observe(viewLifecycleOwner) { list ->
            list.body()?.let {
                adapter.setList(it)
            }
            binding.tvCity.text = list.body()?.city?.name
        }

        binding.btnChoose.setOnClickListener {
            APP.navController.navigate(R.id.action_startFragment_to_choiceFragment)
        }
        binding.btnDetail.setOnClickListener {
            APP.navController.navigate(R.id.action_startFragment_to_detailFragment)
        }

    }

    fun Double.toCoordinats(): String {
        return ((this * 100.0).roundToInt() / 100.0).toString()
    }
}