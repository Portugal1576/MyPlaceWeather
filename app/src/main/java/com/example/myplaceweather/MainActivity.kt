package com.example.myplaceweather

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myplaceweather.databinding.ActivityMainBinding
import com.example.myplaceweather.modelall.model.Coord
import com.example.myplaceweather.utils.APP
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var locClient: FusedLocationProviderClient
    private val mainViewModel: MainViewModel by viewModels()


    //OnBackPress
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            showDialog()
        }
    }

    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP = this
        navController = Navigation.findNavController(this, R.id.nav_host)

        if (checkForInternet(this)) {
            Toast.makeText(this, "Internet Connected", Toast.LENGTH_SHORT).show()
        } else {
            MaterialAlertDialogBuilder(this).apply {
                setTitle("Internet Disconnected")
                setMessage("Application closed")
                setPositiveButton("OK") { _, _ -> finish() }
                show()
            }
        }

        locClient = LocationServices.getFusedLocationProviderClient(this)
        getCurrentLocation()

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(this).apply {
            setTitle("Are you sure?")
            setMessage("Want to close the application ?")
            setPositiveButton("Yes") { _, _ -> finish() }
            setNegativeButton("No", null)
            show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getCurrentLocation() {
        if (checkPermission()) {
            if (isLocationEnabled()) {
                //final latitude and longitude
                locClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        Toast.makeText(this, "Null received", Toast.LENGTH_LONG).show()
                        mainViewModel.coordinates.value = Coord(41.1296529, -7.7850178)

                    } else {
                        Toast.makeText(this, "Location get Success", Toast.LENGTH_LONG).show()

                        mainViewModel.coordinates.value =
                            Coord(location.latitude, location.longitude)
                    }
                }
            } else {
                //settings open here
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ), PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }

    private fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Granted", Toast.LENGTH_LONG).show()
                getCurrentLocation()
            } else {
                Toast.makeText(applicationContext, "Denied", Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

}