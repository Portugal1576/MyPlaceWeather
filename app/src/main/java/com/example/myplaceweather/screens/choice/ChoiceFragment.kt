package com.example.myplaceweather.screens.choice

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.myplaceweather.MainViewModel
import com.example.myplaceweather.R
import com.example.myplaceweather.databinding.FragmentChoiceBinding
import com.example.myplaceweather.model.Coord
import com.example.myplaceweather.utils.APP
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.internal.ViewUtils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_choice.*
import java.util.*

@Suppress("UNREACHABLE_CODE")
class ChoiceFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentChoiceBinding
    @SuppressLint("RestrictedApi")
    private val callback = OnMapReadyCallback { googleMap ->

        val myPlaceLat = mainViewModel.coordinates.value?.lat
        val myPlaceLon = mainViewModel.coordinates.value?.lon
        val myPlace = LatLng(myPlaceLat!!, myPlaceLon!!)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace, 8f))

        binding.btnCity.setOnClickListener {
            val city = binding.etCity.text.toString()
            mainViewModel.getCityCoordinats(city)

            binding.etCity.isVisible = false
            binding.btnCity.isVisible = false
            binding.tvAttention.isVisible = false
            view?.let { it1 -> hideKeyboard(it1) }

            mainViewModel.myCityWeather.observe(viewLifecycleOwner) { list ->
                list.body()?.let {
                    mainViewModel.coordinates.value = it.coord
                    val place = LatLng(it.coord.lat, it.coord.lon)
                    googleMap.clear()
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 10f))
                    googleMap.addMarker(MarkerOptions().position(place))?.isVisible = true
                }
            }
            Handler(Looper.getMainLooper()).postDelayed({
                APP.navController.navigate(
                    R.id.action_choiceFragment_to_detailFragment
                )
            }, 4000)
        }
            binding.btnCity

        googleMap.setOnMapClickListener { latLng ->
            googleMap.clear()
            googleMap.addMarker(MarkerOptions().position(latLng))?.isVisible = true
            binding.etCity.isVisible = false
            binding.btnCity.isVisible = false
            binding.tvAttention.isVisible = false
            view?.let { it1 -> hideKeyboard(it1) }
            googleMap.mapType
            mainViewModel.coordinates.value = Coord(latLng.latitude, latLng.longitude)
            //Delay 1 sec
            Handler(Looper.getMainLooper()).postDelayed({
                showDialog()
            }, 1000)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoiceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        val mapAsync = mapFragment?.getMapAsync(callback)
    }


    private fun showDialog() {
        MaterialAlertDialogBuilder(requireActivity())
            .apply {
                setTitle("Are you sure?")
                setPositiveButton("Yes") { _, _ ->
                    APP.navController.navigate(
                        R.id.action_choiceFragment_to_detailFragment
                    )
                }
                setNegativeButton("No"){ _, _ ->
                    binding.etCity.isVisible = true
                    binding.btnCity.isVisible = true
                    binding.tvAttention.isVisible = true
                }
                show()
            }
    }
    @SuppressLint("ServiceCast")
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
