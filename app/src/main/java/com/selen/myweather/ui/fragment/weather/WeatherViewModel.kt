package com.selen.myweather.ui.fragment.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selen.myweather.api.WeatherApi
import com.selen.myweather.api.response.WeatherResponse
import com.selen.myweather.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Pyaterko Aleksey
 */
class WeatherViewModel : ViewModel() {

    private val api: WeatherApi by lazy { App.instance.getApi() }

    val weatherLiveData = MutableLiveData<WeatherResponse>()

    fun loadWeather(city: String) {
        // TODO: 30.03.2021 добавить mapper
        // TODO: 30.03.2021 вынести загрузку в loader
        // ключ в гите - кошмар
        // бесплатный ключ с тестового аккаунта в демонстрационном приложении - комфорт для просматривающего код :)
        api.loadWeather(city, "metric", "d1467727eb1b785602d006f500e8c523", "ru")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                weatherLiveData.value = response
            }, {
                println("onError: ${it.message}")
            })
    }

}