package com.selen.myweather.ui.fragment.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.selen.myweather.R
import com.selen.myweather.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Начальный экран для отображения погоды
 * */
class WeatherFragment : Fragment() {

    private lateinit var button: Button

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(this).get(WeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view.findViewById(R.id.fragment_weather_button)
        button.setOnClickListener {
            val action = WeatherFragmentDirections.actionWeatherFragmentToSettingsFragment()
            Navigation.findNavController(view).navigate(action)
        }

        // TODO: 14.03.2021 перенести во viewmodel
        App.instance.getApi().loadWeather("Москва", "metric", "d1467727eb1b785602d006f500e8c523")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response
            }, {
                println("onError: ${it.message}")
            })


    }
}