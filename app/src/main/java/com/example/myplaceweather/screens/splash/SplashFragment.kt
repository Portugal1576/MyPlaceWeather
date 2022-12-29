package com.example.myplaceweather.screens.splash

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import com.example.myplaceweather.R
import com.example.myplaceweather.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    @RequiresApi(Build.VERSION_CODES.O)
    private val current = LocalDate.now()
    @RequiresApi(Build.VERSION_CODES.O)
    private val current_month = current.monthValue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)

        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val orientation = view.resources.configuration.orientation
        binding.tvTime.text = current.toString()
        if (orientation == 1){
        when (current_month) {
            12, 1, 2 -> binding.splashContainer.background =
                resources.getDrawable(R.drawable.winter_portrait, context?.theme)
            3, 4, 5 -> binding.splashContainer.background =
                resources.getDrawable(R.drawable.spring_portrait, context?.theme)
            6, 7, 8 -> binding.splashContainer.background =
                resources.getDrawable(R.drawable.summer_portrait, context?.theme)
            else -> {
                binding.splashContainer.background =
                    resources.getDrawable(R.drawable.autumn_portrait, context?.theme)
            }
        }

        }
        else{
            when (current_month) {
                12, 1, 2 -> binding.splashContainer.background =
                    resources.getDrawable(R.drawable.winter_landscape, context?.theme)
                3, 4, 5 -> binding.splashContainer.background =
                    resources.getDrawable(R.drawable.spring_landscape, context?.theme)
                6, 7, 8 -> binding.splashContainer.background =
                    resources.getDrawable(R.drawable.summer_landscape, context?.theme)
                else -> {
                    binding.splashContainer.background =
                        resources.getDrawable(R.drawable.autumn_landscape, context?.theme)
                }
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_splashFragment_to_choiceFragment)
        }
    }
}