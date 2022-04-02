package com.testing.openweatherapp.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.testing.openweatherapp.common.MainNavigationFragment
import com.testing.openweatherapp.data.local.database.model.WeatherEntity
import com.testing.openweatherapp.databinding.FragmentWeatherBinding
import com.testing.openweatherapp.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_weather.*

@AndroidEntryPoint
class WeatherFragment : MainNavigationFragment() {
    private lateinit var binding: FragmentWeatherBinding

    private val viewModel: WeatherViewModel by viewModels()

    private var cityName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = this@WeatherFragment.viewModel
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityName = arguments?.getString(Constants.EXTRAS_CITY_NAME)
        observeViewModel()
        viewModel.loadWeatherFromApi(cityName ?: "Moscow")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    NavHostFragment.findNavController(this@WeatherFragment).navigateUp()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun initializeViews(weatherEntity: WeatherEntity) {
        weatherEntity.weather?.get(0)?.let {
            if (!it.icon.isNullOrEmpty()) {
                val url = Constants.IMAGE_URL.plus(it.icon).plus(Constants.IMAGE_FORMAT)
                ImageLoader.loadImage(imageViewIcon, url)
            }
            textViewDescription.text = it.description.emptyIfNull()
        }
        textViewTemp.text = weatherEntity.temp.tempString()
        textViewFeelsLikeTemp.text = weatherEntity.feelsLike.tempString()
        textViewHumidityValue.text = weatherEntity.humidity.humidityString()
        textViewPressureAboutSeeLevel.text = weatherEntity.seaLevel.pressureString()
        textViewPressureAboutGroundLevel.text = weatherEntity.grndLevel.pressureString()
        textViewWindSpeed.text = weatherEntity.windSpeed.windString()
        textViewWindDeg.text = weatherEntity.windDeg.windDirectionString()
    }

    override fun observeViewModel() {
        cityName?.let {
            viewModel.weatherByName(it).doOnChange(this) { weather ->
                if(weather != null) {
                    initializeViews(weather)
                }
            }
        }

        viewModel.isLoading.doOnChange(this) {
            weatherLoading.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

}