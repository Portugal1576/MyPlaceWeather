package com.example.myplaceweather.screens.splash

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.myplaceweather.R
import com.example.myplaceweather.databinding.FragmentSplashBinding
import com.example.myplaceweather.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val current = LocalDate.now()
    private val month = current.monthValue


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)

        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orientation = view.resources.configuration.orientation
        binding.tvTime.text = SimpleDateFormat("dd-M-yyyy", Locale.getDefault())
            .format(Date()).toString()
        if (orientation == 1) {
            when (month) {
                12, 1, 2 -> binding.splashContainer.background =
                    resources.getDrawable(WINTER, context?.theme)
                3, 4, 5 -> binding.splashContainer.background =
                    resources.getDrawable(SPRING, context?.theme)
                6, 7, 8 -> binding.splashContainer.background =
                    resources.getDrawable(SUMMER, context?.theme)
                else -> {
                    binding.splashContainer.background =
                        resources.getDrawable(AUTUMN, context?.theme)
                }
            }

        } else {
            when (month) {
                12, 1, 2 -> binding.splashContainer.background =
                    resources.getDrawable(WINTER_LAND, context?.theme)
                3, 4, 5 -> binding.splashContainer.background =
                    resources.getDrawable(SPRING_LAND, context?.theme)
                6, 7, 8 -> binding.splashContainer.background =
                    resources.getDrawable(SUMMER_LAND, context?.theme)
                else -> {
                    binding.splashContainer.background =
                        resources.getDrawable(AUTUMN_LAND, context?.theme)
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