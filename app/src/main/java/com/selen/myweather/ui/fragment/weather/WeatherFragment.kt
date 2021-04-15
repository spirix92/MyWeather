package com.selen.myweather.ui.fragment.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var errorGroup: Group
    private lateinit var dataGroup: Group

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

        swipeRefresh.apply {
            setOnRefreshListener {
                viewModel.onSwipeRefresh(currentCityData.currentCitySelected)
            }
            setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
            )
        }

        weatherAdapter = WeatherAdapter()

        weatherRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = weatherAdapter
        }

        viewModel.apply {
            loadingLiveData.observeForever { setLoading(it) }
            weatherLiveData.observeForever { setWeather(it) }
            errorLiveData.observeForever { setError(it) }
        }

        currentCity.setOnClickListener {
            val action = WeatherFragmentDirections.actionWeatherFragmentToMapFragment()
            Navigation.findNavController(currentCity).navigate(action)
        }

        viewModel.loadWeather(currentCityData.currentCitySelected)

    }

    private fun initViews(view: View) {
        swipeRefresh = view.findViewById(R.id.fragment_weather_swipe_refresh)
        dataGroup = view.findViewById(R.id.fragment_weather_group_data)
        errorGroup = view.findViewById(R.id.fragment_weather_group_error)
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

    private fun setLoading(isLoading: Boolean) {
        swipeRefresh.isRefreshing = isLoading
    }

    private fun setError(isError: Boolean) {
        if (isError) {
            errorGroup.visibility = View.VISIBLE
            dataGroup.visibility = View.GONE
        } else {
            errorGroup.visibility = View.GONE
            dataGroup.visibility = View.VISIBLE
        }
    }

}