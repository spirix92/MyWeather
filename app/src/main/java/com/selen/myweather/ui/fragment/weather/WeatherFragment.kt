package com.selen.myweather.ui.fragment.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selen.myweather.R
import com.selen.myweather.api.response.WeatherResponse
import com.selen.myweather.ui.fragment.map.pref.CityPref
import com.selen.myweather.ui.fragment.weather.adapter.WeatherAdapter

/**
 * Начальный экран для отображения погоды
 * */
class WeatherFragment : Fragment() {

    private lateinit var currentWeatherImage: ImageView
    private lateinit var currentCity: TextView
    private lateinit var currentTemp: TextView
    private lateinit var currentWeatherIcon: ImageView
    private lateinit var currentWeatherDescription: TextView
    private lateinit var currentTempFeelLike: TextView

    private lateinit var weatherRecycler: RecyclerView
    private lateinit var weatherAdapter: WeatherAdapter

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(this).get(WeatherViewModel::class.java)
    }

    private val currentCityData = CityPref()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        weatherAdapter = WeatherAdapter()

        weatherRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = weatherAdapter
        }

        viewModel.apply {
            weatherLiveData.observeForever { setWeather(it) }
        }

        currentCity.setOnClickListener {
            val action = WeatherFragmentDirections.actionWeatherFragmentToMapFragment()
            Navigation.findNavController(currentCity).navigate(action)
        }

        viewModel.loadWeather(currentCityData.currentCitySelected)

    }

    private fun initViews(view: View) {
        currentWeatherImage =
            view.findViewById(R.id.fragment_weather_image_view_current_weather_image)
        currentCity = view.findViewById(R.id.fragment_weather_text_view_current_city)
        currentTemp = view.findViewById(R.id.fragment_weather_text_view_current_temp)
        currentWeatherIcon = view.findViewById(R.id.item_weather_image_view_current_weather_icon)
        currentWeatherDescription =
            view.findViewById(R.id.fragment_weather_text_view_current_weather_description)
        currentTempFeelLike =
            view.findViewById(R.id.fragment_weather_text_view_current_temp_feel_like)
        weatherRecycler = view.findViewById(R.id.fragment_weather_recycler_view_weather)
    }

    private fun setWeather(weather: WeatherResponse) {
        setCurrentDay(weather)
        weatherAdapter.setData(weather.list)
    }

    private fun setCurrentDay(weather: WeatherResponse) {
        if (!weather.list.isNullOrEmpty()) {
            val currentWeather = weather.list.first()
            currentCity.text = weather.city.name
            currentTemp.text = "${currentWeather.main.temp.toInt()}°"
            currentWeatherDescription.text = currentWeather.weather.first().description
            currentTempFeelLike.text =
                getString(R.string.current_temp_feel_like, currentWeather.main.feelsLike.toInt())

            Glide
                .with(requireContext())
                .load(
                    getString(R.string.icon_weather_url, currentWeather.weather.first().icon)
                ).into(currentWeatherIcon)

        }
    }

}