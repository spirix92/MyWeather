package com.selen.myweather.api

import com.selen.myweather.api.response.WeatherResponse
import io.reactivex.Single

/**
 * @author Pyaterko Aleksey
 */
class DefaultApiWorker(private val api: WeatherApi) : WeatherApiWorker {

    override fun loadWeather(cityCountry: String, units: String, keyApi: String)
            : Single<WeatherResponse> {
        return api.loadWeather(cityCountry, units, keyApi)
    }

}