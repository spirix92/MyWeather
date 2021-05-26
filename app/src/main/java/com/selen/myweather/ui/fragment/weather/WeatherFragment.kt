package com.selen.myweather.ui.fragment.weather

import android.content.Context
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
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.selen.myweather.R
import com.selen.myweather.api.response.WeatherResponse
import com.selen.myweather.app.App
import com.selen.myweather.pref.CityPref
import com.selen.myweather.pref.TemperatureUnitsPref
import com.selen.myweather.ui.fragment.weather.adapter.NewsAdapter
import com.selen.myweather.ui.fragment.weather.adapter.WeatherAdapter
import com.zhpan.indicator.IndicatorView
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import javax.inject.Inject

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

    private lateinit var newsPager: ViewPager
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsIndicatorView: IndicatorView

    private lateinit var weatherRecycler: RecyclerView
    private lateinit var weatherAdapter: WeatherAdapter

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var errorGroup: Group
    private lateinit var dataGroup: Group

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(this).get(WeatherViewModel::class.java).apply {
            App.instance.appComponent.inject(this)
        }
    }

    @Inject
    lateinit var appContext: Context

    @Inject
    lateinit var currentCityData: CityPref

    @Inject
    lateinit var currentTemperatureUnitsData: TemperatureUnitsPref
    private var currentTemperatureUnits: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.instance.appComponent.inject(this)

        currentTemperatureUnits = getString(currentTemperatureUnitsData.currentUnitSelected.characterResource)

        initViews(view)

        initNewsViewPager()

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
        newsIndicatorView = view.findViewById(R.id.fragment_weather_indicator_view_news)
        newsPager = view.findViewById(R.id.fragment_weather_view_pager_news)
    }

    private fun initNewsViewPager() {
        newsAdapter = NewsAdapter(requireContext())
        newsPager.adapter = newsAdapter
        newsPager.setPadding(80, 0, 80, 0)

        newsAdapter.news = resources.getStringArray(R.array.news_names).toList()
        newsAdapter.notifyDataSetChanged()
        newsIndicatorView.setPageSize(newsAdapter.count)

        newsIndicatorView.apply {
            setSliderColor(R.color.teal_700, R.color.purple_700)
            setSliderWidth(30f)
            setSliderHeight(10f)
            setSlideMode(IndicatorSlideMode.WORM)
            setIndicatorStyle(IndicatorStyle.ROUND_RECT)
            setPageSize(newsAdapter.count)
            notifyDataChanged()
        }

        newsPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                newsIndicatorView.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                newsIndicatorView.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun setWeather(weather: WeatherResponse) {
        setCurrentDay(weather)
        weatherAdapter.setData(
            weather.list,
            currentTemperatureUnits
        )
    }

    private fun setCurrentDay(weather: WeatherResponse) {
        if (!weather.list.isNullOrEmpty()) {
            val currentWeather = weather.list.first()
            currentCity.text = weather.city.name
            currentTemp.text =
                "${currentWeather.main.temp.toInt()}${currentTemperatureUnits}"
            currentWeatherDescription.text = currentWeather.weather.first().description
            currentTempFeelLike.text =
                appContext.getString(
                    R.string.current_temp_feel_like,
                    currentWeather.main.feelsLike.toInt(),
                    currentTemperatureUnits
                )

            Glide
                .with(appContext)
                .load(
                    appContext.getString(R.string.icon_weather_url, currentWeather.weather.first().icon)
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